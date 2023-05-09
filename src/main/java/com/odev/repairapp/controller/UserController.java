package com.odev.repairapp.controller;

import com.odev.repairapp.request.RegisterRequest;
import com.odev.repairapp.response.UserResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.UserService;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGE_USERS')")
public class UserController {

    private final UserService service;


    @PostMapping("/save")
    public MyResponse<UserResponse> save(@RequestBody RegisterRequest request) {
        return MyResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.save(request))
                .build();
    }
}
