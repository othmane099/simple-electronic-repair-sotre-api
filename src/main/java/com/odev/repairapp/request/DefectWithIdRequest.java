package com.odev.repairapp.request;

import com.odev.repairapp.model.Defect;

public record DefectWithIdRequest(Long id, String title, String requiredTime, double cost,
                                  Long deviceId, double price) {

    public static Defect toEntity(DefectWithIdRequest request){
        return Defect.builder()
                .id(request.id())
                .title(request.title())
                .requiredTime(request.requiredTime())
                .cost(request.cost())
                .price(request.price())
                .build();
    }
}
