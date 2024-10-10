package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Laborer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LaborerRepository extends JpaRepository<Laborer, UUID> {

    List<Laborer> findByProjectId(UUID projectId);

    List<Laborer> findByManagerId(UUID managerId);
}
