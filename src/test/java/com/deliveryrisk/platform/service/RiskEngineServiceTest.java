package com.deliveryrisk.platform.service;

import com.deliveryrisk.platform.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RiskEngineServiceTest {

    private final RiskEngineService riskEngine = new RiskEngineService();

    @Test
    void healthyProjectWithNoOpenBugsScoresHigh() {
        Project project = new Project("Healthy Project", "Atlas", ProjectStatus.ON_TRACK, 100);
        Sprint sprint = new Sprint(project, 40, 40);

        int score = riskEngine.healthScore(project, List.of(sprint), List.of());

        assertEquals(100, score);
    }

    @Test
    void openCriticalBugsReduceHealthScore() {
        Project project = new Project("Buggy Project", "Falcon", ProjectStatus.AT_RISK, 80);
        Sprint sprint = new Sprint(project, 40, 40);
        Bug criticalBug = new Bug(project, Severity.CRITICAL, BugStatus.OPEN);

        int score = riskEngine.healthScore(project, List.of(sprint), List.of(criticalBug));

        assertTrue(score < 100, "Open critical bug should reduce health score below 100");
    }

    @Test
    void delayedProjectWithLowHealthIsHighRisk() {
        Project project = new Project("Delayed Project", "Falcon", ProjectStatus.DELAYED, 50);
        Sprint sprint = new Sprint(project, 30, 10);
        Bug bug = new Bug(project, Severity.HIGH, BugStatus.OPEN);

        RiskEngineService.RiskResult result = riskEngine.computeRiskScore(project, List.of(sprint), List.of(bug));

        assertEquals("high", result.level());
    }

    @Test
    void completedProjectWithGoodDeploysIsLowRisk() {
        Project project = new Project("Shipped Project", "Atlas", ProjectStatus.COMPLETED, 99);
        Sprint sprint = new Sprint(project, 40, 40);

        RiskEngineService.RiskResult result = riskEngine.computeRiskScore(project, List.of(sprint), List.of());

        assertEquals("low", result.level());
    }
}
