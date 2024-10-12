package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Engineers;
import org.pragadeesh.gri.service.EngineerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class EngineerController {

    private final EngineerService engineerService;

    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }

    @PostMapping("/engineer/project/{projectId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Engineers> addEngineersToProject(@PathVariable UUID projectId,
                                                           @RequestParam String engineerName) {
        return ResponseEntity.ok(engineerService.addEngineersToProject(projectId, engineerName));
    }

    @GetMapping("/engineer/project")
    public ResponseEntity<List<Engineers>> getAllEngineersByProject(@RequestParam UUID projectId) {
        return ResponseEntity.ok(engineerService.getAllEngineersForProject(projectId));
    }
}
