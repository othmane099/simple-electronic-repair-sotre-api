package com.odev.repairapp.request;

import com.odev.repairapp.model.Brand;

public record BrandRequest(String name) {

    public static Brand toEntity(BrandRequest request){
        return Brand.builder()
                .name(request.name())
                .build();
    }
}
