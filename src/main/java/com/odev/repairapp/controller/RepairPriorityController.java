package com.odev.repairapp.controller;

import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.RepairPriorityRequest;
import com.odev.repairapp.request.RepairPriorityWithIdRequest;
import com.odev.repairapp.response.RepairPriorityResponse;
import com.odev.repairapp.response.wrapper.MyDeleteResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.RepairPriorityService;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repair-priorities")
public class RepairPriorityController {

    private final RepairPriorityService service;

    @GetMapping
    public MyResponse<List<RepairPriorityResponse>> findAll(){
        return MyResponse.<List<RepairPriorityResponse>>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.findAll())
                .build();
    }

    @PostMapping("/save")
    public MyResponse<RepairPriorityResponse> save(@RequestBody RepairPriorityRequest request){
        RepairPriorityResponse data =  service.save(request);
        return MyResponse.<RepairPriorityResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_SAVED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @PostMapping("/update")
    public MyResponse<RepairPriorityResponse> update(@RequestBody RepairPriorityWithIdRequest request){
        RepairPriorityResponse data = service.update(request);
        return MyResponse.<RepairPriorityResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_UPDATED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @GetMapping("/show")
    public MyResponse<RepairPriorityResponse> findById(@RequestBody IdRequest idRequest){
        RepairPriorityResponse data = service.show(idRequest);
        return MyResponse.<RepairPriorityResponse>builder()
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
