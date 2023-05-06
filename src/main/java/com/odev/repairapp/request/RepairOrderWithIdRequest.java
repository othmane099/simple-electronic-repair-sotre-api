package com.odev.repairapp.request;

import com.odev.repairapp.model.RepairOrder;

import java.util.List;
import java.util.stream.Collectors;

public record RepairOrderWithIdRequest(
        Long id,
        String name,
        String email,
        String phone,
        String address,
        String serialNumber,
        String diagnostics,
        double subTotal,
        double totalCost,
        double profit,
        RepairPriorityRequest repairPriorityRequest,
        boolean isDeviceCollected,
        List<DefectRequest> defectRequests,
        BrandRequest brandRequest,
        DeviceRequest deviceRequest
) {

    public static RepairOrder toEntity(RepairOrderWithIdRequest request){
        return RepairOrder.builder()
                .id(request.id())
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .serialNumber(request.serialNumber())
                .subTotal(request.subTotal())
                .totalCost(request.totalCost())
                .repairPriority(RepairPriorityRequest.toEntity(request.repairPriorityRequest))
                .defects(request.defectRequests().stream().map(DefectRequest::toEntity).collect(Collectors.toList()))
                .build();
    }
}
