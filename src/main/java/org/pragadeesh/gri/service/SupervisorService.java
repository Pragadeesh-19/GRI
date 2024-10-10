package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.entity.Supervisor;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.pragadeesh.gri.repository.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupervisorService {

    private final SupervisorRepository supervisorRepository;
    private final ProjectRepository projectRepository;

    public SupervisorService(SupervisorRepository supervisorRepository, ProjectRepository projectRepository) {
        this.supervisorRepository = supervisorRepository;
        this.projectRepository = projectRepository;
    }

    public Supervisor addSupervisorToProject(UUID projectId, String supervisorName) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with projectId " + projectId));

        Supervisor supervisor = new Supervisor();
        supervisor.setName(supervisorName);
        supervisor.setProject(project);

        return supervisorRepository.save(supervisor);
    }

    public List<Supervisor> getAllSupervisorsForProject(UUID projectId) {
        return supervisorRepository.findByProjectId(projectId);
    }

    public List<Supervisor> getAllSupervisorsForManagerId(UUID managerId) {
        return supervisorRepository.findByManagerId(managerId);
    }
}
