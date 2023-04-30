package com.odev.repairapp.response;

import com.odev.repairapp.model.QuickReply;

import java.time.LocalDateTime;

public record QuickReplyResponse(Long id, String name, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static QuickReplyResponse toResponse(QuickReply reply){
        return new QuickReplyResponse(
                reply.getId(),
                reply.getName(),
                reply.getBody(),
                reply.getCreatedAt(),
                reply.getUpdatedAt());
    }
}
