package com.odev.repairapp.request;

import com.odev.repairapp.model.Defect;

public record DefectRequest(String title, String requiredTime, double cost,
                            Long deviceId, double price) {

    public static Defect toEntity(DefectRequest request){
        return Defect.builder()
                .title(request.title())
                .requiredTime(request.requiredTime())
                .cost(request.cost())
                .price(request.price())
                .build();
    }
}
