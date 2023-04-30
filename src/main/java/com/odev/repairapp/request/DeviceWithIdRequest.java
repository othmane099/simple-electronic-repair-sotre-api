package com.odev.repairapp.request;

import com.odev.repairapp.model.Device;

public record DeviceWithIdRequest(Long id, String name, BrandWithIdRequest brand, String model) {

    public static Device toEntity(DeviceWithIdRequest request){
        return Device.builder()
                .id(request.id())
                .name(request.name())
                .brand(BrandWithIdRequest.toEntity(request.brand()))
                .model(request.model())
                .build();
    }
}
