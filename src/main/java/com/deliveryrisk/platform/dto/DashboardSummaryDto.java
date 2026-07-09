package com.deliveryrisk.platform.dto;

public class DashboardSummaryDto {
    private Integer avgHealth;
    private Integer sprintCompletionRate;
    private Integer deliverySuccessRate;
    private Integer highRiskProjectCount;
    private Integer avgDeploySuccessRate;

    public DashboardSummaryDto() {
    }

    public DashboardSummaryDto(Integer avgHealth, Integer sprintCompletionRate,
                                Integer deliverySuccessRate, Integer highRiskProjectCount,
                                Integer avgDeploySuccessRate) {
        this.avgHealth = avgHealth;
        this.sprintCompletionRate = sprintCompletionRate;
        this.deliverySuccessRate = deliverySuccessRate;
        this.highRiskProjectCount = highRiskProjectCount;
        this.avgDeploySuccessRate = avgDeploySuccessRate;
    }

    public Integer getAvgHealth() {
        return avgHealth;
    }

    public void setAvgHealth(Integer avgHealth) {
        this.avgHealth = avgHealth;
    }

    public Integer getSprintCompletionRate() {
        return sprintCompletionRate;
    }

    public void setSprintCompletionRate(Integer sprintCompletionRate) {
        this.sprintCompletionRate = sprintCompletionRate;
    }

    public Integer getDeliverySuccessRate() {
        return deliverySuccessRate;
    }

    public void setDeliverySuccessRate(Integer deliverySuccessRate) {
        this.deliverySuccessRate = deliverySuccessRate;
    }

    public Integer getHighRiskProjectCount() {
        return highRiskProjectCount;
    }

    public void setHighRiskProjectCount(Integer highRiskProjectCount) {
        this.highRiskProjectCount = highRiskProjectCount;
    }

    public Integer getAvgDeploySuccessRate() {
        return avgDeploySuccessRate;
    }

    public void setAvgDeploySuccessRate(Integer avgDeploySuccessRate) {
        this.avgDeploySuccessRate = avgDeploySuccessRate;
    }
}
