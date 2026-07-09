package com.deliveryrisk.platform.service;

import com.deliveryrisk.platform.dto.BugDto;
import com.deliveryrisk.platform.model.Bug;
import com.deliveryrisk.platform.model.BugStatus;
import com.deliveryrisk.platform.repository.BugRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugService {

    private final BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public List<BugDto> findAll() {
        return bugRepository.findAll().stream().map(this::toDto).toList();
    }

    public List<BugDto> findOpen() {
        return bugRepository.findByStatus(BugStatus.OPEN).stream().map(this::toDto).toList();
    }

    public List<BugDto> findByProjectId(Long projectId) {
        return bugRepository.findByProjectId(projectId).stream().map(this::toDto).toList();
    }

    private BugDto toDto(Bug b) {
        return new BugDto(
                b.getId(),
                b.getProject().getId(),
                b.getSeverity().name().toLowerCase(),
                b.getStatus().name().toLowerCase()
        );
    }
}
