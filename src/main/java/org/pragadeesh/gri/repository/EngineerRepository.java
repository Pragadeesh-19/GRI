package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Engineers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EngineerRepository extends JpaRepository<Engineers, UUID> {

    List<Engineers> findByProjectId(UUID projectId);

    List<Engineers> findByManagerId(UUID managerId);
}

