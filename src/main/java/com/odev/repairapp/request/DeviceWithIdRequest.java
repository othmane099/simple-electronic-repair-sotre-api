package com.odev.repairapp.request;

import com.odev.repairapp.model.Device;

public record DeviceWithIdRequest(Long id, String name, String model, Long brandId) {

    public static Device toEntity(DeviceWithIdRequest request){
        return Device.builder()
                .id(request.id())
                .name(request.name())
                .model(request.model())
                .build();
    }
}
