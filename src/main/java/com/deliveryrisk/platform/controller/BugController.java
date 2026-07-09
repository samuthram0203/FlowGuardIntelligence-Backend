package com.deliveryrisk.platform.controller;

import com.deliveryrisk.platform.dto.BugDto;
import com.deliveryrisk.platform.service.BugService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping
    public List<BugDto> getAll(
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) Boolean openOnly
    ) {
        if (projectId != null) {
            return bugService.findByProjectId(projectId);
        }
        if (Boolean.TRUE.equals(openOnly)) {
            return bugService.findOpen();
        }
        return bugService.findAll();
    }
}
