package com.odev.repairapp.response;

import com.odev.repairapp.model.Authority;
import com.odev.repairapp.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(Long id, String firstname, String lastname, String email,
                           List<Authority> authorities, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getAuthority(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
