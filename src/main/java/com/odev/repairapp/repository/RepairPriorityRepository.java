package com.odev.repairapp.repository;

import com.odev.repairapp.model.RepairPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairPriorityRepository extends JpaRepository<RepairPriority, Long> {

    Optional<RepairPriority> findByName(String name);
}
