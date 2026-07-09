package com.deliveryrisk.platform.service;

import com.deliveryrisk.platform.model.Bug;
import com.deliveryrisk.platform.model.BugStatus;
import com.deliveryrisk.platform.model.Project;
import com.deliveryrisk.platform.model.ProjectStatus;
import com.deliveryrisk.platform.model.Sprint;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Core scoring logic for the Delivery Risk Intelligence Platform.
 * Mirrors src/lib/riskEngine.js from the frontend so both sides of the stack
 * compute the same numbers.
 */
@Service
public class RiskEngineService {

    public int healthScore(Project project, List<Sprint> sprints, List<Bug> bugs) {
        int planned = sprints.stream().mapToInt(Sprint::getPointsPlanned).sum();
        int completed = sprints.stream().mapToInt(Sprint::getPointsCompleted).sum();
        double sprintCompletionRate = planned > 0 ? (double) completed / planned : 1.0;

        int openBugWeight = bugs.stream()
                .filter(b -> b.getStatus() == BugStatus.OPEN)
                .mapToInt(b -> b.getSeverity().getWeight())
                .sum();

        double base = sprintCompletionRate * 100;
        double bugPenalty = Math.min(40, openBugWeight * 4);

        return (int) Math.max(0, Math.round(base - bugPenalty));
    }

    public RiskResult computeRiskScore(Project project, List<Sprint> sprints, List<Bug> bugs) {
        int health = healthScore(project, sprints, bugs);
        int deploy = project.getDeploySuccessRate() != null ? project.getDeploySuccessRate() : 100;

        int statusPenalty = switch (project.getStatus()) {
            case DELAYED -> 25;
            case AT_RISK -> 12;
            default -> 0;
        };

        double raw = (100 - health) * 0.5 + (100 - deploy) * 0.4 + statusPenalty;
        int score = (int) Math.max(0, Math.min(100, Math.round(raw)));

        String level = score >= 65 ? "high" : score >= 35 ? "medium" : "low";

        return new RiskResult(score, level);
    }

    public record RiskResult(int score, String level) {
    }
}
