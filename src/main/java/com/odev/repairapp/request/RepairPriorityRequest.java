package com.odev.repairapp.request;

import com.odev.repairapp.model.RepairPriority;

public record RepairPriorityRequest(Integer priorityValue, String name, double extraCharge) {


    public static RepairPriority toEntity(RepairPriorityRequest request){
        return RepairPriority.builder()
                .priorityValue(request.priorityValue)
                .name(request.name())
                .extraCharge(request.extraCharge)
                .build();
    }
}
