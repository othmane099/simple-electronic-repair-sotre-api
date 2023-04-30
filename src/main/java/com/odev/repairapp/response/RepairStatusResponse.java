package com.odev.repairapp.response;

import com.odev.repairapp.model.RepairStatus;

import java.time.LocalDateTime;

public record RepairStatusResponse(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static RepairStatusResponse toResponse(RepairStatus repairStatus){
        return new RepairStatusResponse(
                repairStatus.getId(),
                repairStatus.getName(),
                repairStatus.getCreatedAt(),
                repairStatus.getUpdatedAt());
    }
}
