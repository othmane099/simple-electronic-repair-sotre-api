package com.odev.repairapp.controller;

import com.odev.repairapp.request.RepairOrderRequest;
import com.odev.repairapp.request.RepairOrderWithIdRequest;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.filter.FilterRepairOrderRequest;
import com.odev.repairapp.response.RepairOrderResponse;
import com.odev.repairapp.response.wrapper.MyDeleteResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.RepairOrderService;
import com.odev.repairapp.utils.Constant;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repair-orders")
public class RepairOrderController {
    private final RepairOrderService service;

    @GetMapping
    public MyResponse<Page<RepairOrderResponse>> findAll(
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_STRING) int page,
            @RequestParam(defaultValue = Constant.DEFAULT_SIZE_STRING) int size,
            @RequestBody FilterRepairOrderRequest request
            ){
        return MyResponse.<Page<RepairOrderResponse>>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.findAll(page, size, request))
                .build();
    }

    @PostMapping("/save")
    public MyResponse<RepairOrderResponse> save(@RequestBody RepairOrderRequest request){
        RepairOrderResponse data =  service.save(request);
        return MyResponse.<RepairOrderResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_SAVED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @PostMapping("/update")
    public MyResponse<RepairOrderResponse> update(@RequestBody RepairOrderWithIdRequest request){
        RepairOrderResponse data = service.update(request);
        return MyResponse.<RepairOrderResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_UPDATED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @GetMapping("/show")
    public MyResponse<RepairOrderResponse> findById(@RequestBody IdRequest idRequest){
        RepairOrderResponse data = service.show(idRequest);
        return MyResponse.<RepairOrderResponse>builder()
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
