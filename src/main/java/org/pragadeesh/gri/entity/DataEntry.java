package org.pragadeesh.gri.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "data_entries")
@Data
public class DataEntry {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String data;

    private String azureBlobReference;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
