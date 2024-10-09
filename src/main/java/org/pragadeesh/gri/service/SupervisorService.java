package org.pragadeesh.gri.service;

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
        List<Supervisor> supervisors = supervisorRepository.findSupervisorByProjectId(projectId);

        // Check if a supervisor with the same name already exists
        if (supervisors.stream().anyMatch(s -> s.getName().equals(supervisorName))) {
            throw new RuntimeException("Duplicate supervisor not allowed");
        }

        // Save the new supervisor
        Supervisor supervisor = new Supervisor();
        supervisor.setName(supervisorName);
        supervisor.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found")));

        return supervisorRepository.save(supervisor);
    }

    public List<Supervisor> getAllSupervisorsForProject(UUID projectId) {
        return supervisorRepository.findSupervisorByProjectId(projectId);
    }

    public List<Supervisor> getAllSupervisorsForManagerId(UUID managerId) {
        return supervisorRepository.findSupervisorByManagerId(managerId);
    }
}
