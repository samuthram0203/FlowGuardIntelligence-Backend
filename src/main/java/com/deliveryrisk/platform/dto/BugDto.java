package com.deliveryrisk.platform.dto;

public class BugDto {
    private Long id;
    private Long projectId;
    private String severity;
    private String status;

    public BugDto() {
    }

    public BugDto(Long id, Long projectId, String severity, String status) {
        this.id = id;
        this.projectId = projectId;
        this.severity = severity;
        this.status = status;
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

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
