package com.odev.repairapp.request;

import com.odev.repairapp.model.Authority;
import com.odev.repairapp.model.Brand;
import com.odev.repairapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record RegisterRequest(String firstname, String lastname, String email, String password,
                              List<Authority> authorityList) {

    public static User toEntity(RegisterRequest request){
        return User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .authority(request.authorityList())
                .build();
    }
}