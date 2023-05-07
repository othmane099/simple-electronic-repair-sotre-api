package com.odev.repairapp.request;

import com.odev.repairapp.model.Device;

public record DeviceRequest(String name, String model, Long brandId) {

    public static Device toEntity(DeviceRequest request){
        return Device.builder()
                .name(request.name())
                .model(request.model())
                .build();
    }
}
