package com.deliveryrisk.platform.config;

import com.deliveryrisk.platform.model.*;
import com.deliveryrisk.platform.repository.BugRepository;
import com.deliveryrisk.platform.repository.ProjectRepository;
import com.deliveryrisk.platform.repository.SprintRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds the in-memory H2 database with the same sample projects/sprints/bugs
 * used by the frontend's mock API (src/api/portfolio.js), so the two stay
 * consistent while you wire up a real data source.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final SprintRepository sprintRepository;
    private final BugRepository bugRepository;

    public DataSeeder(ProjectRepository projectRepository,
                       SprintRepository sprintRepository,
                       BugRepository bugRepository) {
        this.projectRepository = projectRepository;
        this.sprintRepository = sprintRepository;
        this.bugRepository = bugRepository;
    }

    @Override
    public void run(String... args) {
        if (projectRepository.count() > 0) {
            return;
        }

        Project p1 = projectRepository.save(new Project("Customer Portal Revamp", "Atlas", ProjectStatus.ON_TRACK, 96));
        Project p2 = projectRepository.save(new Project("Payments Gateway v2", "Falcon", ProjectStatus.AT_RISK, 71));
        Project p3 = projectRepository.save(new Project("Internal Analytics Hub", "Nimbus", ProjectStatus.ON_TRACK, 88));
        Project p4 = projectRepository.save(new Project("Mobile Onboarding Flow", "Falcon", ProjectStatus.DELAYED, 54));
        Project p5 = projectRepository.save(new Project("Vendor Integration API", "Atlas", ProjectStatus.COMPLETED, 99));
        Project p6 = projectRepository.save(new Project("Support Ticket AI Triage", "Nimbus", ProjectStatus.ON_TRACK, 91));

        sprintRepository.save(new Sprint(p1, 40, 38));
        sprintRepository.save(new Sprint(p2, 32, 18));
        sprintRepository.save(new Sprint(p3, 28, 26));
        sprintRepository.save(new Sprint(p4, 30, 12));
        sprintRepository.save(new Sprint(p6, 24, 22));

        bugRepository.save(new Bug(p1, Severity.LOW, BugStatus.CLOSED));
        bugRepository.save(new Bug(p2, Severity.HIGH, BugStatus.OPEN));
        bugRepository.save(new Bug(p2, Severity.CRITICAL, BugStatus.OPEN));
        bugRepository.save(new Bug(p4, Severity.HIGH, BugStatus.OPEN));
        bugRepository.save(new Bug(p4, Severity.MEDIUM, BugStatus.OPEN));
        bugRepository.save(new Bug(p3, Severity.LOW, BugStatus.CLOSED));
        bugRepository.save(new Bug(p6, Severity.MEDIUM, BugStatus.CLOSED));
    }
}
