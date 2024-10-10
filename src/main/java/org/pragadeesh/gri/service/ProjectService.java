package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.User;
import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.repository.UserRepository;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project createProject(String projectName) {
        Project project = new Project();
        project.setName(projectName);
        return projectRepository.save(project);
    }

    public Project assignProjectToManager(UUID projectId, UUID managerId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project Not found with ProjectId " + projectId));

        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found with managerId " + managerId));

        project.setAssignedManager(manager);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjectForManager(UUID managerId) {
        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found with managerId " + managerId));

        return projectRepository.findByAssignedManager(manager);
    }
}
