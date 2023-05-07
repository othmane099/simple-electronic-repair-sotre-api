package com.odev.repairapp.response;

import com.odev.repairapp.model.Brand;
import com.odev.repairapp.model.QuickReply;

import java.time.LocalDateTime;

public record BrandResponse(Long id, String name, boolean hasDevices, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static BrandResponse toResponse(Brand brand){
        return new BrandResponse(
                brand.getId(),
                brand.getName(),
                brand.beingUsed(),
                brand.getCreatedAt(),
                brand.getUpdatedAt());
    }
}
