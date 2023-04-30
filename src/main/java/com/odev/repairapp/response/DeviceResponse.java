package com.odev.repairapp.response;

import com.odev.repairapp.model.Device;

import java.time.LocalDateTime;

public record DeviceResponse(
        Long id,
        String name,
        String model,
        String avatar,
        BrandResponse brandResponse,
        int totalDefects,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static DeviceResponse toResponse(Device device){
        return new DeviceResponse(
                device.getId(),
                device.getName(),
                device.getModel(),
                device.getAvatar(),
                BrandResponse.toResponse(device.getBrand()),
                device.getDefects().size(),
                device.getCreatedAt(),
                device.getUpdatedAt());
    }
}
