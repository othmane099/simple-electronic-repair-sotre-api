package com.odev.repairapp.repository;

import com.odev.repairapp.model.RepairStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairStatusRepository extends JpaRepository<RepairStatus, Long> {
    Optional<RepairStatus> findByName(String name);
}
