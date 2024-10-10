package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperatorRepository extends JpaRepository<Operators, UUID> {

    List<Operators> findByProjectId(UUID projectId);

    List<Operators> findByManagerId(UUID managerId);
}
