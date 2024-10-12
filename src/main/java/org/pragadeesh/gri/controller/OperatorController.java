package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Operators;
import org.pragadeesh.gri.service.OperatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/manager")
public class OperatorController {

    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @PostMapping("/operator/project")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<Operators> addOperatorToProject(@RequestParam UUID projectId,
                                                          @RequestParam String operatorName) {
        return ResponseEntity.ok(operatorService.addOperatorToProject(projectId, operatorName));
    }

    @GetMapping("/operator/project/{projectId}")
    public ResponseEntity<List<Operators>> getOperatorsByProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(operatorService.getAllOperatorsForProject(projectId));
    }
}
