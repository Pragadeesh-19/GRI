package org.pragadeesh.gri.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String vehicleType;

    private String vehicleImageUrl;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
