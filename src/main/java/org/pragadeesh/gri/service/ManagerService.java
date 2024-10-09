package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.DataEntry;
import org.pragadeesh.gri.entity.Manager;
import org.pragadeesh.gri.repository.DataEntryRepository;
import org.pragadeesh.gri.repository.ManagerRepository;
import org.pragadeesh.gri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManagerService {

    private final DataEntryRepository dataEntryRepository;
    private final ProjectRepository projectRepository;
    private final ManagerRepository managerRepository;
    private final AzureStorageService azureStorageService;


    public ManagerService(DataEntryRepository dataEntryRepository, ProjectRepository projectRepository, ManagerRepository managerRepository, AzureStorageService azureStorageService) {
        this.dataEntryRepository = dataEntryRepository;
        this.projectRepository = projectRepository;
        this.managerRepository = managerRepository;
        this.azureStorageService = azureStorageService;
    }

    public DataEntry submitDataEntry(String data, UUID projectId, UUID managerId) {
        String azureBlobReference = azureStorageService.uploadData(data);

        DataEntry dataEntry = new DataEntry();
        dataEntry.setTimestamp(LocalDateTime.now());
        dataEntry.setAzureBlobReference(azureBlobReference);
        dataEntry.setProject(projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not found!!")));
        dataEntry.setManager(managerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager Not found!!")));

        return dataEntryRepository.save(dataEntry);
    }
}
