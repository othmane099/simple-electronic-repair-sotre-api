package com.odev.repairapp.response;

import com.odev.repairapp.model.RepairPriority;
import lombok.Builder;

@Builder
public record RepairPriorityResponse(Long id, Integer priorityValue, String name, double extraCharge) {

    public static RepairPriorityResponse toResponse(RepairPriority priority){
        return new RepairPriorityResponse(
                priority.getId(),
                priority.getPriorityValue(),
                priority.getName(),
                priority.getExtraCharge());
    }
}
