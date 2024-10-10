package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Engineers;
import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.repository.EngineerRepository;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EngineerService {

    private final EngineerRepository engineerRepository;
    private final ProjectRepository projectRepository;

    public EngineerService(EngineerRepository engineerRepository, ProjectRepository projectRepository) {
        this.engineerRepository = engineerRepository;
        this.projectRepository = projectRepository;
    }

    public Engineers addEngineersToProject(UUID projectId, String engineerName) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ProjectId " + projectId));

        Engineers engineers = new Engineers();
        engineers.setName(engineerName);
        engineers.setProject(project);

        return engineerRepository.save(engineers);
    }

    public List<Engineers> getAllEngineersForProject(UUID projectId) {
        return engineerRepository.findByProjectId(projectId);
    }

    public List<Engineers> getAllEngineersForManager(UUID managerId) {
        return engineerRepository.findByManagerId(managerId);
    }
}
