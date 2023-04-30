package com.odev.repairapp.controller;

import com.odev.repairapp.request.*;
import com.odev.repairapp.response.BrandResponse;
import com.odev.repairapp.response.RepairStatusResponse;
import com.odev.repairapp.response.wrapper.MyDeleteResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.BrandService;
import com.odev.repairapp.service.RepairStatusService;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repair-statuses")
public class RepairStatusController {

    private final RepairStatusService service;

    @GetMapping
    public MyResponse<List<RepairStatusResponse>> findAll(){
        return MyResponse.<List<RepairStatusResponse>>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.findAll())
                .build();
    }

    @PostMapping("/save")
    public MyResponse<RepairStatusResponse> save(@RequestBody RepairStatusRequest request){
        RepairStatusResponse data =  service.save(request);
        return MyResponse.<RepairStatusResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_SAVED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @PostMapping("/update")
    public MyResponse<RepairStatusResponse> update(@RequestBody RepairStatusWithIdRequest request){
        RepairStatusResponse data = service.update(request);
        return MyResponse.<RepairStatusResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_UPDATED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @GetMapping("/show")
    public MyResponse<RepairStatusResponse> findById(@RequestBody IdRequest idRequest){
        RepairStatusResponse data = service.show(idRequest);
        return MyResponse.<RepairStatusResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(data)
                .build();

    }

    @DeleteMapping("/delete")
    public MyDeleteResponse deleteById(@RequestBody IdRequest idRequest){
        service.deleteById(idRequest);
        return MyDeleteResponse.builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_REMOVED_SUCCESSFULLY)
                .build();
    }
}
