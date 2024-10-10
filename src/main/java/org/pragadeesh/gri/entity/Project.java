package org.pragadeesh.gri.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "projects")
@Data
public class Project {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "assigned_manager_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_project_manager"))
    private User assignedManager;
}
