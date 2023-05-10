package com.odev.repairapp.controller;

import com.odev.repairapp.request.DashboardRequest;
import com.odev.repairapp.response.DashboardResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.DashboardService;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ACCESS_DASHBOARD')")
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService service;
    @GetMapping
    public MyResponse<DashboardResponse> index(@RequestBody DashboardRequest request){
        return MyResponse.<DashboardResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.getStats(request))
                .build();
    }
}
