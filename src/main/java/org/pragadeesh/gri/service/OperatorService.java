package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.Operators;
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
        Operators operators = new Operators();
        operators.setName(operatorName);
        operators.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with projectId " + projectId)));

        return operatorRepository.save(operators);
    }

    public List<Operators> getAllOperatorsForProject(UUID projectId) {
        return operatorRepository.findOperatorsByProjectId(projectId);
    }
}
