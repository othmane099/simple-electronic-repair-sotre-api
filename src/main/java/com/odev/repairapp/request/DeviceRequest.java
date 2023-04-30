package com.odev.repairapp.request;

import com.odev.repairapp.model.Device;

public record DeviceRequest(String name, BrandWithIdRequest brand, String model) {

    public static Device toEntity(DeviceRequest request){
        return Device.builder()
                .name(request.name())
                .brand(BrandWithIdRequest.toEntity(request.brand()))
                .model(request.model())
                .build();
    }
}
