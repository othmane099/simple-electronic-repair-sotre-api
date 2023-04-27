package com.odev.repairapp.request;

import com.odev.repairapp.model.RepairPriority;

public record RepairPriorityWithIdRequest(Long id, Integer priorityValue, String name, double extraCharge) {
    public static RepairPriority toEntity(RepairPriorityWithIdRequest request){
        return RepairPriority.builder()
                .id(request.id)
                .priorityValue(request.priorityValue)
                .name(request.name())
                .extraCharge(request.extraCharge)
                .build();
    }
}
