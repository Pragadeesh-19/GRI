package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.entity.Technicians;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.pragadeesh.gri.repository.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final ProjectRepository projectRepository;

    public TechnicianService(TechnicianRepository technicianRepository, ProjectRepository projectRepository) {
        this.technicianRepository = technicianRepository;
        this.projectRepository = projectRepository;
    }

    public Technicians addTechnicianToProject(UUID projectId, String technicianName) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ProjectId " + projectId));

        Technicians technicians = new Technicians();
        technicians.setName(technicianName);
        technicians.setProject(project);

        return technicianRepository.save(technicians);
    }

    public List<Technicians> getAllTechniciansForProject(UUID projectId) {
        return technicianRepository.findByProjectId(projectId);
    }

    public List<Technicians> getAllTechniciansForManager(UUID managerId) {
        return technicianRepository.findByManagerId(managerId);
    }
}
