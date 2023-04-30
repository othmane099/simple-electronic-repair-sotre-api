package com.odev.repairapp.request;

import com.odev.repairapp.model.QuickReply;

public record QuickReplyWithIdRequest(Long id, String name, String body) {

    public static QuickReply toEntity(QuickReplyWithIdRequest request){
        return QuickReply.builder()
                .id(request.id())
                .name(request.name())
                .body(request.body())
                .build();
    }
}
