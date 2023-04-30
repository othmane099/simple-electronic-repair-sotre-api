package com.odev.repairapp.request;

import com.odev.repairapp.model.QuickReply;
import com.odev.repairapp.model.RepairPriority;

public record QuickReplyRequest(String name, String body) {

    public static QuickReply toEntity(QuickReplyRequest request){
        return QuickReply.builder()
                .name(request.name())
                .body(request.body())
                .build();
    }
}
