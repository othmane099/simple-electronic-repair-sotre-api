package com.odev.repairapp.controller;

import com.odev.repairapp.model.Authority;
import com.odev.repairapp.request.*;
import com.odev.repairapp.request.filter.FilterQuickReplyRequest;
import com.odev.repairapp.request.filter.FilterRequest;
import com.odev.repairapp.response.QuickReplyResponse;
import com.odev.repairapp.response.wrapper.MyDeleteResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.QuickReplyService;
import com.odev.repairapp.utils.Constant;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quick-replies")
@PreAuthorize("hasAuthority('MANAGE_QUICK_REPLY')")
public class QuickReplyController {
    private final QuickReplyService service;

    @GetMapping
    public MyResponse<Page<QuickReplyResponse>> findAll(
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_STRING) int page,
            @RequestParam(defaultValue = Constant.DEFAULT_SIZE_STRING) int size,
            @RequestBody FilterQuickReplyRequest request
            ){
        return MyResponse.<Page<QuickReplyResponse>>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.findAll(page, size, request))
                .build();
    }

    @PostMapping("/save")
    public MyResponse<QuickReplyResponse> save(@RequestBody QuickReplyRequest request){
        QuickReplyResponse data =  service.save(request);
        return MyResponse.<QuickReplyResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_SAVED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @PostMapping("/update")
    public MyResponse<QuickReplyResponse> update(@RequestBody QuickReplyWithIdRequest request){
        QuickReplyResponse data = service.update(request);
        return MyResponse.<QuickReplyResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_UPDATED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @GetMapping("/show")
    public MyResponse<QuickReplyResponse> findById(@RequestBody IdRequest idRequest){
        QuickReplyResponse data = service.show(idRequest);
        return MyResponse.<QuickReplyResponse>builder()
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
