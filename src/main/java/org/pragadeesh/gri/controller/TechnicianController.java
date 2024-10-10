package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Technicians;
import org.pragadeesh.gri.service.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @PostMapping("/technician/project/{projectId}")
    public ResponseEntity<Technicians> addTechniciansToProject(@PathVariable UUID projectId,
                                                               @RequestParam String technicianName) {
        return ResponseEntity.ok(technicianService.addTechnicianToProject(projectId, technicianName));
    }

    @GetMapping("/technician/project/{projectId}")
    public ResponseEntity<List<Technicians>> getAllTechniciansForProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(technicianService.getAllTechniciansForProject(projectId));
    }
}
