package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.Device;
import com.odev.repairapp.model.filter_key.DeviceFilterKey;
import com.odev.repairapp.repository.DeviceRepository;
import com.odev.repairapp.repository.RepairOrderRepository;
import com.odev.repairapp.request.DeviceRequest;
import com.odev.repairapp.request.DeviceWithIdRequest;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.filter.FilterDeviceRequest;
import com.odev.repairapp.response.DeviceResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.utils.Helper;
import com.odev.repairapp.validator.DeviceValidator;
import com.odev.repairapp.validator.filter.FilterDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repository;
    private final RepairOrderRepository repairOrderRepository;

    public Page<DeviceResponse> findAll(int pageNum, int pageSize, FilterDeviceRequest request) {
        List<String> errors = FilterDataValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Device filter is not valid",
                    ErrorCode.DEVICE_NOT_VALID,
                    errors);

        Pageable pageable = PageRequest.of(
                pageNum,
                pageSize,
                Sort.by(
                        Sort.Direction.fromString(request.sorting().name().toLowerCase()),
                        request.getFilterKey().getValue()
                )
        );

        return repository.findAll(
                (root, cq, cb) -> cb.like(
                        root.get(DeviceFilterKey.NAME.getValue()),
                        "%" + request.keyword() + "%"
                ),
                pageable
        ).map(DeviceResponse::toResponse);
    }

    public DeviceResponse save(DeviceRequest request){

        List<String> errors = DeviceValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException(
                    "Device is not valid",
                    ErrorCode.DEVICE_NOT_VALID,
                    errors
            );

        // Check if object already exists in database
        Optional<Device> optionalDevice = repository.findByName(request.name());
        if (optionalDevice.isPresent())
            throw new RepairAppException(
                    "Device's name should be unique",
                    ErrorCode.DEVICE_ALREADY_IN_USE
            );

        Device device = repository.save(DeviceRequest.toEntity(request));
        return DeviceResponse.toResponse(device);
    }

    public DeviceResponse update(DeviceWithIdRequest request){
        List<String> errors = DeviceValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Device is not valid", ErrorCode.DEVICE_NOT_VALID, errors);

        // Check if object already exists in database
        Optional<Device> optionalDevice = repository.findById(request.id());
        if (optionalDevice.isEmpty())
            throw new RepairAppException(
                    "Device is not found",
                    ErrorCode.DEVICE_NOT_FOUND
            );

        Device device = repository.save(DeviceWithIdRequest.toEntity(request));
        return DeviceResponse.toResponse(device);
    }

    public void deleteById(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        Device device = myFindById(idRequest);
        if (device.getDefects().size() > 0 || repairOrderRepository.countByDevice(device) > 0){
            throw new RepairAppException(
                    "Unable to delete data is being used",
                    ErrorCode.DEVICE_IS_RELATED_TO_EXISTING_DEFECT_OR_REPAIR_ORDER
            );
        }
        repository.deleteById(idRequest.id());
    }

    public DeviceResponse show(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        Device device = myFindById(idRequest);
        return DeviceResponse.toResponse(device);

    }

    private Device myFindById(IdRequest idRequest){
        return repository
                .findById(idRequest.id())
                .orElseThrow(() -> new RepairAppException(
                        "No data found with ID = "+idRequest.id(),
                        ErrorCode.DEVICE_NOT_FOUND
                ));
    }
}
