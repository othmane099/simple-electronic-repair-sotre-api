package com.odev.repairapp.request;

import com.odev.repairapp.model.RepairStatus;

public record RepairStatusRequest(String name) {

    public static RepairStatus toEntity(RepairStatusRequest request){
        return RepairStatus.builder()
                .name(request.name())
                .build();
    }
}
