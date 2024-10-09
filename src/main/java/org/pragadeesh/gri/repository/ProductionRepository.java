package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductionRepository extends JpaRepository<Production, UUID> {

    List<Production> findProductionByProjectId(UUID projectId);
}
