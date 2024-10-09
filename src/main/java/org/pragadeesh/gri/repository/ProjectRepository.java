package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Manager;
import org.pragadeesh.gri.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findAllByAssignedManager(UUID managerId);
}
