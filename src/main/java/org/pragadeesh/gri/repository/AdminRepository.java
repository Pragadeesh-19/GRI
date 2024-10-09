package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Admin findByUsername(String username);
}
