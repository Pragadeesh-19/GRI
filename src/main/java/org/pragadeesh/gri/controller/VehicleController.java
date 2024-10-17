package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Vehicle;
import org.pragadeesh.gri.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle/project/{projectId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Vehicle> uploadVehiclePhotos(@PathVariable UUID projectId,
                                                       @RequestPart("file") List<MultipartFile> file,
                                                       @RequestParam String vehicleName,
                                                       @RequestParam String vehicleType) throws Exception {
        return ResponseEntity.ok(vehicleService.uploadVehiclePhotos(projectId, file, vehicleName, vehicleType));
    }

    @GetMapping("/vehicle/project/{projectId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public List<Vehicle> getAllVehicleByProject(@PathVariable UUID projectId) {
        return vehicleService.getAllVehiclesForProject(projectId);
    }
}
