package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Supervisor;
import org.pragadeesh.gri.service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class SupervisorController {

    private final SupervisorService supervisorService;

    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @PostMapping("/supervisor/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Supervisor> addSupervisor(@RequestParam UUID projectId,
                                                    @RequestParam String supervisorName) {
        return ResponseEntity.ok(supervisorService.addSupervisorToProject(projectId, supervisorName));
    }

    @GetMapping("/supervisor/project/{projectId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Supervisor>> getAllSupervisorForProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(supervisorService.getAllSupervisorsForProject(projectId));
    }

    @GetMapping("/supervisor/manager/{managerId}")
    public ResponseEntity<List<Supervisor>> getAllSupervisorForManager(@PathVariable UUID managerId) {
        return ResponseEntity.ok(supervisorService.getAllSupervisorsForManagerId(managerId));
    }
}
