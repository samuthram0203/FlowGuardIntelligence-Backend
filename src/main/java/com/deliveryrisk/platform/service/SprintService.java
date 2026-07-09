package com.deliveryrisk.platform.service;

import com.deliveryrisk.platform.dto.SprintDto;
import com.deliveryrisk.platform.model.Sprint;
import com.deliveryrisk.platform.repository.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public List<SprintDto> findAll() {
        return sprintRepository.findAll().stream().map(this::toDto).toList();
    }

    public List<SprintDto> findByProjectId(Long projectId) {
        return sprintRepository.findByProjectId(projectId).stream().map(this::toDto).toList();
    }

    private SprintDto toDto(Sprint s) {
        return new SprintDto(s.getId(), s.getProject().getId(), s.getPointsPlanned(), s.getPointsCompleted());
    }
}
