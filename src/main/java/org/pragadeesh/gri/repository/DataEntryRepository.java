package org.pragadeesh.gri.repository;

import org.pragadeesh.gri.entity.DataEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface DataEntryRepository extends JpaRepository<DataEntry, UUID> {

    List<DataEntry> findByProjectIdAndTimestampBetween(UUID projectId, LocalDateTime start, LocalDateTime end);
}
