package com.deliveryrisk.platform.service;

import com.deliveryrisk.platform.dto.ProjectDto;
import com.deliveryrisk.platform.model.Project;
import com.deliveryrisk.platform.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(this::toDto).toList();
    }

    public ProjectDto findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + id));
        return toDto(project);
    }

    private ProjectDto toDto(Project p) {
        return new ProjectDto(
                p.getId(),
                p.getName(),
                p.getTeam(),
                p.getStatus().name().toLowerCase(),
                p.getDeploySuccessRate()
        );
    }
}
