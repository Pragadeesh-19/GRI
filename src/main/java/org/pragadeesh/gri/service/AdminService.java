package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Admin;
import org.pragadeesh.gri.entity.Manager;
import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.repository.AdminRepository;
import org.pragadeesh.gri.repository.ManagerRepository;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ProjectRepository projectRepository;
    private final ManagerRepository managerRepository;

    public AdminService(AdminRepository adminRepository, ProjectRepository projectRepository, ManagerRepository managerRepository) {
        this.adminRepository = adminRepository;
        this.projectRepository = projectRepository;
        this.managerRepository = managerRepository;
    }

    public Project assignProjectToManager(UUID projectId, UUID managerId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project Not found!!"));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found!!"));

        project.setManager(manager);
        return projectRepository.save(project);
    }

    public Admin findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
