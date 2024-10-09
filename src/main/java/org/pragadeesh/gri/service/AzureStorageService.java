package org.pragadeesh.gri.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
public class AzureStorageService {

    private final BlobServiceClient blobServiceClient;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    public AzureStorageService(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    public String uploadData(String data) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        String blobName = UUID.randomUUID().toString();
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        try (ByteArrayInputStream dataStream = new ByteArrayInputStream(data.getBytes())){
            blobClient.upload(dataStream, data.length());
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload data to Azure blob storage");
        }
        return blobName;
    }

    public String downloadData(String blobName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            blobClient.downloadStream(outputStream);
            return outputStream.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to download data from Azure blob storage");
        }
    }
}
