package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Laborer;
import org.pragadeesh.gri.service.LaborerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class LaborerController {

    private final LaborerService laborerService;

    public LaborerController(LaborerService laborerService) {
        this.laborerService = laborerService;
    }

    @PostMapping("/laborer/add")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<Laborer> addLaborerToProject(@RequestParam UUID projectId,
                                                       @RequestParam String laborerName) {
        return ResponseEntity.ok(laborerService.addLaborerToProject(projectId, laborerName));
    }

    @GetMapping("/laborer/project")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Laborer>> getAllLaborerForProject(@RequestParam UUID projectId) {
        return ResponseEntity.ok(laborerService.getAllLaborersForProject(projectId));
    }

    @GetMapping("/laborer/manager")
    public ResponseEntity<List<Laborer>> getAllLaborerForManager(@RequestParam UUID managerId) {
        return ResponseEntity.ok(laborerService.getAllLabourersForManager(managerId));
    }
}
