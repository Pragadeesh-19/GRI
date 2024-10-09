package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    List<Vehicle> findVehiclesByProjectId(UUID projectId);

    List<Vehicle> findVehiclesByManagerId(UUID managerId);
}
