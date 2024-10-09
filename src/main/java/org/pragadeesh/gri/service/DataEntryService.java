package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.DataEntry;
import org.pragadeesh.gri.repository.DataEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DataEntryService {

    private final DataEntryRepository dataEntryRepository;
    private final AzureStorageService azureStorageService;

    public DataEntryService(DataEntryRepository dataEntryRepository, AzureStorageService azureStorageService) {
        this.dataEntryRepository = dataEntryRepository;
        this.azureStorageService = azureStorageService;
    }

    public List<String> getDataEntryByProjectAndDataRange(UUID projectId, LocalDateTime start, LocalDateTime end) {
        List<DataEntry> dataEntries = dataEntryRepository.findByProjectIdAndTimestampBetween(projectId, start, end);
        return dataEntries.stream()
                .map(entry -> azureStorageService.downloadData(entry.getAzureBlobReference()))
                .collect(Collectors.toList());
    }
}
