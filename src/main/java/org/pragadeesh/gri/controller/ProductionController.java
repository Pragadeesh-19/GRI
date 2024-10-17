package org.pragadeesh.gri.controller;

import org.pragadeesh.gri.entity.Production;
import org.pragadeesh.gri.service.ProductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping("/production/project/{projectId}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    public ResponseEntity<Production> logProductionTime(@PathVariable UUID projectId,
                                                        @RequestParam LocalDateTime start,
                                                        @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(productionService.logProductionTime(projectId, start, end));
    }

    @GetMapping("/production/project/{projectId}")
    public ResponseEntity<List<Production>> getAllProductionLogsByProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(productionService.getAllProductionLogsForProject(projectId));
    }
}
