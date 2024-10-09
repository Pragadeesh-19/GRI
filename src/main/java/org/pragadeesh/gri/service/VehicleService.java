package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Vehicle;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.pragadeesh.gri.repository.VehicleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Repository
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ProjectRepository projectRepository;
    private final AzureBlobStorageService azureBlobStorageService;

    public VehicleService(VehicleRepository vehicleRepository, ProjectRepository projectRepository, AzureBlobStorageService azureBlobStorageService) {
        this.vehicleRepository = vehicleRepository;
        this.projectRepository = projectRepository;
        this.azureBlobStorageService = azureBlobStorageService;
    }

    public Vehicle uploadVehicleReading(UUID projectId, MultipartFile file) throws Exception {

        String imageUrl = azureBlobStorageService.uploadVehicleReadingImage(file);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleImageUrl(imageUrl);
        vehicle.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with projectId " + projectId)));

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehiclesForProject(UUID projectId) {
        return vehicleRepository.findVehiclesByProjectId(projectId);
    }
}
