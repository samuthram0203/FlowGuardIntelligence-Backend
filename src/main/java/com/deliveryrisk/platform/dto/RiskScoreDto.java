package com.deliveryrisk.platform.dto;

public class RiskScoreDto {
    private Long projectId;
    private String projectName;
    private Integer healthScore;
    private Integer riskScore;
    private String riskLevel;

    public RiskScoreDto() {
    }

    public RiskScoreDto(Long projectId, String projectName, Integer healthScore,
                         Integer riskScore, String riskLevel) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.healthScore = healthScore;
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Integer healthScore) {
        this.healthScore = healthScore;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
