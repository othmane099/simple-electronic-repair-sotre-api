package com.odev.repairapp.repository;

import com.odev.repairapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long>, JpaSpecificationExecutor<RepairOrder> {
    Long countByRepairPriority(RepairPriority priority);
    Long countByRepairStatus(RepairStatus repairStatus);

    Long countByDevice(Device device);
}
