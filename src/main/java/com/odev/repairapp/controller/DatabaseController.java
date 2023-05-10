package com.odev.repairapp.controller;

import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@PreAuthorize("hasAuthority('DATABASE_BACKUP')")
@RequiredArgsConstructor
public class DatabaseController {

    private final DatabaseService service;
    @GetMapping("/backup")
    public MyResponse<Object> backup() throws IOException, InterruptedException {
        service.backup();
        return MyResponse.builder()
                .status(HttpStatus.OK)
                .message("file generated successfully")
                .data(null)
                .build();
    }

}
