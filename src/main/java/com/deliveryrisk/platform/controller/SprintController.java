package com.deliveryrisk.platform.controller;

import com.deliveryrisk.platform.dto.SprintDto;
import com.deliveryrisk.platform.service.SprintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    public List<SprintDto> getAll(@RequestParam(required = false) Long projectId) {
        return projectId != null ? sprintService.findByProjectId(projectId) : sprintService.findAll();
    }
}
