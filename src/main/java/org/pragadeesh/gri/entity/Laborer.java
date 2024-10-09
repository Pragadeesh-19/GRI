package org.pragadeesh.gri.entity;

import jakarta.persistence.*;
import jakarta.websocket.OnOpen;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "laborer")
@Data
public class Laborer {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
