package com.deliveryrisk.platform.dto;

public class ProjectDto {
    private Long id;
    private String name;
    private String team;
    private String status;
    private Integer deploySuccessRate;

    public ProjectDto() {
    }

    public ProjectDto(Long id, String name, String team, String status, Integer deploySuccessRate) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeploySuccessRate() {
        return deploySuccessRate;
    }

    public void setDeploySuccessRate(Integer deploySuccessRate) {
        this.deploySuccessRate = deploySuccessRate;
    }
}
