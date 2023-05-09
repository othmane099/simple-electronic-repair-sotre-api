package com.odev.repairapp.controller;

import com.odev.repairapp.request.AuthenticationRequest;
import com.odev.repairapp.request.RegisterRequest;
import com.odev.repairapp.response.AuthenticationResponse;
import com.odev.repairapp.response.UserResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.AuthenticationService;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  @PreAuthorize("hasAuthority('MANAGE_USERS')")
  public MyResponse<UserResponse> register(@RequestBody RegisterRequest request) {
    return MyResponse.<UserResponse>builder()
            .status(HttpStatus.OK.value())
            .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
            .data(service.register(request))
            .build();
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}