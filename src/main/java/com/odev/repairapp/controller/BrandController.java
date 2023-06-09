package com.odev.repairapp.controller;

import com.odev.repairapp.request.BrandRequest;
import com.odev.repairapp.request.BrandWithIdRequest;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.response.BrandResponse;
import com.odev.repairapp.response.wrapper.MyDeleteResponse;
import com.odev.repairapp.response.wrapper.MyResponse;
import com.odev.repairapp.service.BrandService;
import com.odev.repairapp.utils.CSVHelper;
import com.odev.repairapp.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.odev.repairapp.utils.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
@PreAuthorize("hasAuthority('MANAGE_BRAND')")
public class BrandController {

    private final BrandService service;

    @GetMapping
    public MyResponse<List<BrandResponse>> findAll(){
        return MyResponse.<List<BrandResponse>>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_FETCHED_SUCCESSFULLY)
                .data(service.findAll())
                .build();
    }

    @PostMapping("/save")
    public MyResponse<BrandResponse> save(@RequestBody BrandRequest request){
        BrandResponse data =  service.save(request);
        return MyResponse.<BrandResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_SAVED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @PostMapping("/save/csv")
    public MyResponse<List<BrandResponse>> uploadFile(@RequestParam("file") MultipartFile file) {
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                return MyResponse.<List<BrandResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message(CSV_FILE_UPLOADED_SUCCESSFULLY)
                        .data(service.save(file))
                        .build();
            } catch (Exception e) {
                return MyResponse.<List<BrandResponse>>builder()
                        .status(HttpStatus.EXPECTATION_FAILED.value())
                        .message(CSV_FILE_UPLOADING_FAILED)
                        .data(null)
                        .build();
            }
        }

        return MyResponse.<List<BrandResponse>>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(FILE_NOT_VALID)
                .data(null)
                .build();
    }
    @PostMapping("/update")
    public MyResponse<BrandResponse> update(@RequestBody BrandWithIdRequest request){
        BrandResponse data = service.update(request);
        return MyResponse.<BrandResponse>builder()
                .status(HttpStatus.OK.value())
                .message(ResponseMessage.DATA_UPDATED_SUCCESSFULLY)
                .data(data)
                .build();
    }

    @GetMapping("/show")
    public MyResponse<BrandResponse> findById(@RequestBody IdRequest idRequest){
        BrandResponse data = service.show(idRequest);
        return MyResponse.<BrandResponse>builder()
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
