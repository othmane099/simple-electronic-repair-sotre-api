package com.odev.repairapp.repository;

import com.odev.repairapp.model.RepairOrder;
import com.odev.repairapp.model.RepairPriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    Long countByRepairPriority(RepairPriority priority);
}
