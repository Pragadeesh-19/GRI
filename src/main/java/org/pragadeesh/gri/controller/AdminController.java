package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ProjectService projectService;

    public AdminController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project/{projectId}/manager/{managerId}")
    public ResponseEntity<Project> assignProjectToManager(@PathVariable UUID projectId,
                                                          @PathVariable UUID managerId) {
        return ResponseEntity.ok(projectService.assignProjectToManager(projectId, managerId));
    }
}
