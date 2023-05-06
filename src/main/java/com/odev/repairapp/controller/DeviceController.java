package com.odev.repairapp.controller;

import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.DeviceRequest;
import com.odev.repairapp.request.DeviceWithIdRequest;
import com.odev.repairapp.request.filter.FilterDeviceRequest;
import com.odev.repairapp.response.DeviceResponse;
import com.odev.repairapp.response.wrapper.MyDeleteResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.DeviceService;
import com.odev.repairapp.utils.Constant;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService service;

    @GetMapping
    public MyResponse<Page<DeviceResponse>> findAll(
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_STRING) int page,
            @RequestParam(defaultValue = Constant.DEFAULT_SIZE_STRING) int size,
            @RequestBody FilterDeviceRequest request
            ){
        return MyResponse.<Page<DeviceResponse>>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.findAll(page, size, request))
                .build();
    }

    @PostMapping("/save")
    public MyResponse<DeviceResponse> save(@RequestBody DeviceRequest request){
        DeviceResponse data =  service.save(request);
        return MyResponse.<DeviceResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_SAVED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @PostMapping("/update")
    public MyResponse<DeviceResponse> update(@RequestBody DeviceWithIdRequest request){
        DeviceResponse data = service.update(request);
        return MyResponse.<DeviceResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_UPDATED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @GetMapping("/show")
    public MyResponse<DeviceResponse> findById(@RequestBody IdRequest idRequest){
        DeviceResponse data = service.show(idRequest);
        return MyResponse.<DeviceResponse>builder()
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
