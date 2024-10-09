package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Laborer;
import org.pragadeesh.gri.repository.LaborerRepository;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LaborerService {

    private final LaborerRepository laborerRepository;
    private final ProjectRepository projectRepository;

    public LaborerService(LaborerRepository laborerRepository, ProjectRepository projectRepository) {
        this.laborerRepository = laborerRepository;
        this.projectRepository = projectRepository;
    }

    public Laborer addLaborerToProject(UUID projectId, String laborerName) {
        Laborer laborer = new Laborer();
        laborer.setName(laborerName);
        laborer.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with projectId " + projectId)));

        return laborerRepository.save(laborer);
    }

    public List<Laborer> getAllLaborersForProject(UUID projectId) {
        return laborerRepository.findLaborerByProjectId(projectId);
    }
}
