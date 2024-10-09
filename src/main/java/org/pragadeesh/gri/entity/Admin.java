package org.pragadeesh.gri.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "admins")
@Data
public class Admin {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;
}
