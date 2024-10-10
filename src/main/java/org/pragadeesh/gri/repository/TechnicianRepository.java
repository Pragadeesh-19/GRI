package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Technicians;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TechnicianRepository extends JpaRepository<Technicians, UUID> {

    List<Technicians> findByProjectId(UUID projectId);

    List<Technicians> findByManagerId(UUID managerId);
}


