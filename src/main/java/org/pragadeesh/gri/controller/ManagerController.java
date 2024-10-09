package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/submit-data")
    public ResponseEntity<?> submitDataEntry(@RequestBody String data,
                                             @RequestParam UUID projectId,
                                             @RequestParam UUID managerId) {
        try {
            return ResponseEntity.ok(managerService.submitDataEntry(data, projectId, managerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
