package com.odev.repairapp.request;

import com.odev.repairapp.model.RepairOrder;

import java.util.List;

public record RepairOrderRequest(
        String name,
        String email,
        String phone,
        String address,
        String serialNumber,
        String diagnostics,
        double totalCost,
        double profit,
        double prePaid,
        double totalCharge,
        Long repairPriorityId,
        List<Long> defectsIds,
        Long deviceId,
        Long repairStatusId
) {

    public static RepairOrder toEntity(RepairOrderRequest request){
        return RepairOrder.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .serialNumber(request.serialNumber())
                .totalCharge(request.totalCharge())
                .totalCost(request.totalCost())
                .build();
    }
}
