package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Project> createProject(@RequestParam String projectName) {
        return ResponseEntity.ok(projectService.createProject(projectName));
    }

    // Available only to admin
    @PostMapping("/{projectId}/assign/{managerId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Project> assignProject(@PathVariable UUID projectId,
                                                 @PathVariable UUID managerId) {
        return ResponseEntity.ok(projectService.assignProjectToManager(projectId, managerId));
    }

    // available both to admin and managers
    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Project>> getProjectsForManagers(@PathVariable UUID managerId) {
        return ResponseEntity.ok(projectService.getAllProjectForManager(managerId));
    }
}
