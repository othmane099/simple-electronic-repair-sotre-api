package com.odev.repairapp.controller;

import com.odev.repairapp.request.DefectRequest;
import com.odev.repairapp.request.DefectWithIdRequest;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.filter.FilterDefectRequest;
import com.odev.repairapp.response.DefectResponse;
import com.odev.repairapp.response.wrapper.MyDeleteResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.DefectService;
import com.odev.repairapp.utils.Constant;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/defects")
@PreAuthorize("hasAuthority('MANAGE_DEFECT')")
public class DefectController {
    private final DefectService service;

    @GetMapping
    public MyResponse<Page<DefectResponse>> findAll(
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_STRING) int page,
            @RequestParam(defaultValue = Constant.DEFAULT_SIZE_STRING) int size,
            @RequestBody FilterDefectRequest request
            ){
        return MyResponse.<Page<DefectResponse>>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.findAll(page, size, request))
                .build();
    }

    @PostMapping("/save")
    public MyResponse<DefectResponse> save(@RequestBody DefectRequest request){
        DefectResponse data =  service.save(request);
        return MyResponse.<DefectResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_SAVED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @PostMapping("/update")
    public MyResponse<DefectResponse> update(@RequestBody DefectWithIdRequest request){
        DefectResponse data = service.update(request);
        return MyResponse.<DefectResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_UPDATED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @GetMapping("/show")
    public MyResponse<DefectResponse> findById(@RequestBody IdRequest idRequest){
        DefectResponse data = service.show(idRequest);
        return MyResponse.<DefectResponse>builder()
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
