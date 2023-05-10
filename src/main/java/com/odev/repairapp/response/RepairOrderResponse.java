package com.odev.repairapp.response;

import com.odev.repairapp.model.RepairOrder;

import java.time.LocalDateTime;

public record RepairOrderResponse(
        Long id,
        String uuid,
        String name,
        String email,
        String phone,
        String address,
        String tracking,
        String serialNumber,
        double totalCharges,
        double totalCost,
        int defects,
        RepairPriorityResponse priorityResponse,
        RepairStatusResponse repairStatusResponse,
        boolean paymentStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static RepairOrderResponse toResponse(RepairOrder repairOrder){
        return new RepairOrderResponse(
                repairOrder.getId(),
                repairOrder.getUuid().toString(),
                repairOrder.getName(),
                repairOrder.getEmail(),
                repairOrder.getPhone(),
                repairOrder.getAddress(),
                repairOrder.getTracking(),
                repairOrder.getSerialNumber(),
                repairOrder.getTotalCharge(),
                repairOrder.getTotalCost(),
                repairOrder.getDefects().size(),
                RepairPriorityResponse.toResponse(repairOrder.getRepairPriority()),
                RepairStatusResponse.toResponse(repairOrder.getRepairStatus()),
                repairOrder.isPaymentStatus(),
                repairOrder.getCreatedAt(),
                repairOrder.getUpdatedAt());
    }
}
