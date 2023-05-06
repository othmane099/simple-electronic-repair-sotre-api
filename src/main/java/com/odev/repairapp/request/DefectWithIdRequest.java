package com.odev.repairapp.request;

import com.odev.repairapp.model.Defect;

public record DefectWithIdRequest(Long id, String title, String requiredTime, double cost,
                                  DeviceWithIdRequest device, double price) {

    public static Defect toEntity(DefectWithIdRequest request){
        return Defect.builder()
                .id(request.id())
                .title(request.title())
                .requiredTime(request.requiredTime())
                .cost(request.cost())
                .device(DeviceWithIdRequest.toEntity(request.device()))
                .price(request.price())
                .build();
    }
}
