package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Vehicle;
import org.pragadeesh.gri.service.VehicleService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Vehicle> uploadVehiclePhotos(@PathVariable UUID projectId,
                                                       @RequestBody List<MultipartFile> file) throws Exception {
        return ResponseEntity.ok(vehicleService.uploadVehiclePhotos(projectId, file));
    }

    @GetMapping("/vehicle/project/{projectId}")
    public List<Vehicle> getAllVehicleByProject(@PathVariable UUID projectId) {
        return vehicleService.getAllVehiclesForProject(projectId);
    }
}
