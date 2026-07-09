package com.deliveryrisk.platform.repository;

import com.deliveryrisk.platform.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
