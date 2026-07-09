package com.deliveryrisk.platform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String team;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    /** 0-100 percentage of successful deploys for this project. */
    @Column(name = "deploy_success_rate", nullable = false)
    private Integer deploySuccessRate = 100;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprints = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bug> bugs = new ArrayList<>();

    public Project() {
    }

    public Project(String name, String team, ProjectStatus status, Integer deploySuccessRate) {
        this.name = name;
        this.team = team;
        this.status = status;
        this.deploySuccessRate = deploySuccessRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Integer getDeploySuccessRate() {
        return deploySuccessRate;
    }

    public void setDeploySuccessRate(Integer deploySuccessRate) {
        this.deploySuccessRate = deploySuccessRate;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public List<Bug> getBugs() {
        return bugs;
    }
}
