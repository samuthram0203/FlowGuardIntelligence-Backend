package com.deliveryrisk.platform.controller;

import com.deliveryrisk.platform.dto.ProjectDto;
import com.deliveryrisk.platform.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDto> getAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDto getOne(@PathVariable Long id) {
        return projectService.findById(id);
    }
}
