package com.deliveryrisk.platform.dto;

public class SprintDto {
    private Long id;
    private Long projectId;
    private Integer pointsPlanned;
    private Integer pointsCompleted;

    public SprintDto() {
    }

    public SprintDto(Long id, Long projectId, Integer pointsPlanned, Integer pointsCompleted) {
        this.id = id;
        this.projectId = projectId;
        this.pointsPlanned = pointsPlanned;
        this.pointsCompleted = pointsCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
