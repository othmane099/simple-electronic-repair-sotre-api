package com.odev.repairapp.request;

import com.odev.repairapp.model.Defect;

public record DefectRequest(String title, String requiredTime, double cost,
                            DeviceWithIdRequest device, double price) {

    public static Defect toEntity(DefectRequest request){
        return Defect.builder()
                .title(request.title())
                .requiredTime(request.requiredTime())
                .cost(request.cost())
                .device(DeviceWithIdRequest.toEntity(request.device()))
                .price(request.price())
                .build();
    }
}
