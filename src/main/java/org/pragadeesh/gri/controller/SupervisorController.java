package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Supervisor;
import org.pragadeesh.gri.service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/supervisors")
public class SupervisorController {

    private final SupervisorService supervisorService;

    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Supervisor> addSupervisor(UUID projectId, String supervisorName) {
        return ResponseEntity.ok(supervisorService.addSupervisorToProject(projectId, supervisorName));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Supervisor>> getAllSupervisorForProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(supervisorService.getAllSupervisorsForProject(projectId));
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Supervisor>> getAllSupervisorForManager(@PathVariable UUID managerId) {
        return ResponseEntity.ok(supervisorService.getAllSupervisorsForManagerId(managerId));
    }
}
