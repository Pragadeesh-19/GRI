package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/assign-project")
    public ResponseEntity<?> assignProjectToManager(@RequestParam UUID projectId,
                                                    @RequestParam UUID managerId) {
        try {
            return ResponseEntity.ok(adminService.assignProjectToManager(projectId, managerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
