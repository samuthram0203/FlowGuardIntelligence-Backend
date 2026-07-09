package com.deliveryrisk.platform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Min(0)
    @Column(name = "points_planned", nullable = false)
    private Integer pointsPlanned = 0;

    @Min(0)
    @Column(name = "points_completed", nullable = false)
    private Integer pointsCompleted = 0;

    public Sprint() {
    }

    public Sprint(Project project, Integer pointsPlanned, Integer pointsCompleted) {
        this.project = project;
        this.pointsPlanned = pointsPlanned;
        this.pointsCompleted = pointsCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getPointsPlanned() {
        return pointsPlanned;
    }

    public void setPointsPlanned(Integer pointsPlanned) {
        this.pointsPlanned = pointsPlanned;
    }

    public Integer getPointsCompleted() {
        return pointsCompleted;
    }

    public void setPointsCompleted(Integer pointsCompleted) {
        this.pointsCompleted = pointsCompleted;
    }
}
