package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.entity.Vehicle;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.pragadeesh.gri.repository.VehicleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    public Vehicle uploadVehiclePhotos(UUID projectId, List<MultipartFile> photos) throws Exception {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with projectId " + projectId));

        List<String> imgUrls = new ArrayList<>();
        for (MultipartFile photo : photos) {
            String imageUrl = azureBlobStorageService.uploadFile(photo);
            imgUrls.add(imageUrl);
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleImageUrl(imgUrls);
        vehicle.setProject(project);

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehiclesForProject(UUID projectId) {
        return vehicleRepository.findByProjectId(projectId);
    }

    public List<Vehicle> getAllVehiclesForManager(UUID managerId) {
        return vehicleRepository.findByManagerId(managerId);
    }
}
