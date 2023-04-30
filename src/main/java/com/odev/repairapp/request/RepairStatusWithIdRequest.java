package com.odev.repairapp.request;

import com.odev.repairapp.model.RepairStatus;

public record RepairStatusWithIdRequest(Long id, String name) {

    public static RepairStatus toEntity(RepairStatusWithIdRequest request){
        return RepairStatus.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }
}
