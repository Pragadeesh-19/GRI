package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, UUID> {

    List<Supervisor> findByProjectId(UUID projectId);

    List<Supervisor> findByManagerId(UUID managerId);
}
