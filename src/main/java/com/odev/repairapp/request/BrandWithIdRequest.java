package com.odev.repairapp.request;

import com.odev.repairapp.model.Brand;

public record BrandWithIdRequest(Long id, String name) {

    public static Brand toEntity(BrandWithIdRequest request){
        return Brand.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }
}
