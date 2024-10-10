package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Production;
import org.pragadeesh.gri.repository.ProductionRepository;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductionService {

    private final ProductionRepository productionRepository;
    private final ProjectRepository projectRepository;

    public ProductionService(ProductionRepository productionRepository, ProjectRepository projectRepository) {
        this.productionRepository = productionRepository;
        this.projectRepository = projectRepository;
    }

    public Production logProductionTime(UUID projectId, LocalDateTime start, LocalDateTime end) {
        Production production = new Production();
        production.setStartTime(start);
        production.setEndTime(end);
        production.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with projectID " + projectId)));

        return productionRepository.save(production);
    }

    public List<Production> getAllProductionLogsForProject(UUID projectId) {
        return productionRepository.findByProjectId(projectId);
    }
}
