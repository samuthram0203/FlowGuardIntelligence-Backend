package com.deliveryrisk.platform.service;

import com.deliveryrisk.platform.dto.DashboardSummaryDto;
import com.deliveryrisk.platform.dto.RiskScoreDto;
import com.deliveryrisk.platform.model.Bug;
import com.deliveryrisk.platform.model.Project;
import com.deliveryrisk.platform.model.ProjectStatus;
import com.deliveryrisk.platform.model.Sprint;
import com.deliveryrisk.platform.repository.BugRepository;
import com.deliveryrisk.platform.repository.ProjectRepository;
import com.deliveryrisk.platform.repository.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;
    private final SprintRepository sprintRepository;
    private final BugRepository bugRepository;
    private final RiskEngineService riskEngineService;

    public DashboardService(ProjectRepository projectRepository,
                             SprintRepository sprintRepository,
                             BugRepository bugRepository,
                             RiskEngineService riskEngineService) {
        this.projectRepository = projectRepository;
        this.sprintRepository = sprintRepository;
        this.bugRepository = bugRepository;
        this.riskEngineService = riskEngineService;
    }

    public DashboardSummaryDto getSummary() {
        List<Project> allProjects = projectRepository.findAll();
        List<Sprint> allSprints = sprintRepository.findAll();

        List<Project> active = allProjects.stream()
                .filter(p -> p.getStatus() != ProjectStatus.COMPLETED)
                .toList();

        int avgHealth = active.isEmpty() ? 0 : (int) Math.round(
                active.stream()
                        .mapToInt(p -> riskEngineService.healthScore(p, sprintsFor(p), bugsFor(p)))
                        .average()
                        .orElse(0)
        );

        int planned = allSprints.stream().mapToInt(Sprint::getPointsPlanned).sum();
        int completed = allSprints.stream().mapToInt(Sprint::getPointsCompleted).sum();
        int sprintRate = planned > 0 ? (int) Math.round((completed * 100.0) / planned) : 0;

        long onTime = allProjects.stream()
                .filter(p -> p.getStatus() == ProjectStatus.ON_TRACK || p.getStatus() == ProjectStatus.COMPLETED)
                .count();
        int deliveryRate = allProjects.isEmpty() ? 0
                : (int) Math.round((onTime * 100.0) / allProjects.size());

        long highRisk = active.stream()
                .filter(p -> riskEngineService.computeRiskScore(p, sprintsFor(p), bugsFor(p)).score() >= 65)
                .count();

        int avgDeploy = active.isEmpty() ? 0 : (int) Math.round(
                active.stream()
                        .mapToInt(p -> p.getDeploySuccessRate() != null ? p.getDeploySuccessRate() : 0)
                        .average()
                        .orElse(0)
        );

        return new DashboardSummaryDto(avgHealth, sprintRate, deliveryRate, (int) highRisk, avgDeploy);
    }

    public List<RiskScoreDto> getRiskRanking() {
        List<Project> allProjects = projectRepository.findAll();

        return allProjects.stream()
                .map(p -> {
                    List<Sprint> sprints = sprintsFor(p);
                    List<Bug> bugs = bugsFor(p);
                    int health = riskEngineService.healthScore(p, sprints, bugs);
                    RiskEngineService.RiskResult risk = riskEngineService.computeRiskScore(p, sprints, bugs);
                    return new RiskScoreDto(p.getId(), p.getName(), health, risk.score(), risk.level());
                })
                .sorted(Comparator.comparingInt(RiskScoreDto::getRiskScore).reversed())
                .toList();
    }

    private List<Sprint> sprintsFor(Project p) {
        return sprintRepository.findByProjectId(p.getId());
    }

    private List<Bug> bugsFor(Project p) {
        return bugRepository.findByProjectId(p.getId());
    }
}
