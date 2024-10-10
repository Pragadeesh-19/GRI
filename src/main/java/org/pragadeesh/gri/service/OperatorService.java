package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Operators;
import org.pragadeesh.gri.entity.Project;
import org.pragadeesh.gri.repository.OperatorRepository;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final ProjectRepository projectRepository;

    public OperatorService(OperatorRepository operatorRepository, ProjectRepository projectRepository) {
        this.operatorRepository = operatorRepository;
        this.projectRepository = projectRepository;
    }

    public Operators addOperatorToProject(UUID projectId, String operatorName) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ProjectId " + projectId));

        Operators operators = new Operators();
        operators.setName(operatorName);
        operators.setProject(project);

        return operatorRepository.save(operators);
    }

    public List<Operators> getAllOperatorsForProject(UUID projectId) {
        return operatorRepository.findByProjectId(projectId);
    }

    public List<Operators> getAllOperatorsForManager(UUID managerId) {
        return operatorRepository.findByManagerId(managerId);
    }
}
