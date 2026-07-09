package com.deliveryrisk.platform.repository;

import com.deliveryrisk.platform.model.Bug;
import com.deliveryrisk.platform.model.BugStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {
    List<Bug> findByProjectId(Long projectId);
    List<Bug> findByStatus(BugStatus status);
}
