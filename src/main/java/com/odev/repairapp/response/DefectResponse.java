package com.odev.repairapp.response;

import com.odev.repairapp.model.Defect;

import java.time.LocalDateTime;

public record DefectResponse(
        Long id,
        String title,
        String requiredTime,
        double price,
        double cost,
        DeviceResponse device,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static DefectResponse toResponse(Defect defect){
        return new DefectResponse(
                defect.getId(),
                defect.getTitle(),
                defect.getRequiredTime(),
                defect.getPrice(),
                defect.getCost(),
                DeviceResponse.toResponse(defect.getDevice()),
                defect.getCreatedAt(),
                defect.getUpdatedAt());
    }
}
