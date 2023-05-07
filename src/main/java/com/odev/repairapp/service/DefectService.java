package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.Defect;
import com.odev.repairapp.model.Device;
import com.odev.repairapp.model.filter_key.DefectFilterKey;
import com.odev.repairapp.repository.DefectRepository;
import com.odev.repairapp.repository.DeviceRepository;
import com.odev.repairapp.request.DefectRequest;
import com.odev.repairapp.request.DefectWithIdRequest;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.filter.FilterDefectRequest;
import com.odev.repairapp.response.DefectResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.utils.Helper;
import com.odev.repairapp.validator.DefectValidator;
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
public class DefectService {

    private final DefectRepository repository;
    private final DeviceRepository deviceRepository;
    public Page<DefectResponse> findAll(int pageNum, int pageSize, FilterDefectRequest request) {
        List<String> errors = FilterDataValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Defect filter is not valid",
                    ErrorCode.DEFECT_NOT_VALID,
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
                        root.get(DefectFilterKey.TITLE.getValue()),
                        "%" + request.keyword() + "%"
                ),
                pageable
        ).map(DefectResponse::toResponse);
    }

    public DefectResponse save(DefectRequest request){

        List<String> errors = DefectValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException(
                    "Defect is not valid",
                    ErrorCode.DEFECT_NOT_VALID,
                    errors
            );

        Device device = deviceRepository
                .findById(request.deviceId())
                .orElseThrow(() -> new RepairAppException(
                        "Defect's device not found",
                        ErrorCode.DEVICE_NOT_FOUND
                ));

        Defect defect = DefectRequest.toEntity(request);
        defect.setDevice(device);
        defect = repository.save(defect);
        return DefectResponse.toResponse(defect);
    }

    public DefectResponse update(DefectWithIdRequest request){
        List<String> errors = DefectValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Defect is not valid", ErrorCode.DEFECT_NOT_VALID, errors);

        // Check if object already exists in database
        Optional<Defect> optionalDefect = repository.findById(request.id());
        if (optionalDefect.isEmpty())
            throw new RepairAppException(
                    "Defect is not found",
                    ErrorCode.DEFECT_NOT_FOUND
            );

        Device device = deviceRepository
                .findById(request.deviceId())
                .orElseThrow(() -> new RepairAppException(
                        "Defect's device not found",
                        ErrorCode.DEVICE_NOT_FOUND
                ));

        Defect defect = DefectWithIdRequest.toEntity(request);
        defect.setDevice(device);
        System.out.println(defect);
        defect = repository.saveAndFlush(defect);
        return DefectResponse.toResponse(defect);
    }

    public void deleteById(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        Defect defect = myFindById(idRequest);
        if (defect.getRepairOrders().size() > 0){
            throw new RepairAppException(
                    "Unable to delete data is being used",
                    ErrorCode.DEFECT_IS_RELATED_TO_EXISTING_DEFECT_OR_REPAIR_ORDER
            );
        }
        repository.deleteById(idRequest.id());
    }

    public DefectResponse show(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        Defect defect = myFindById(idRequest);
        return DefectResponse.toResponse(defect);

    }

    private Defect myFindById(IdRequest idRequest){
        return repository
                .findById(idRequest.id())
                .orElseThrow(() -> new RepairAppException(
                        "No data found with ID = "+idRequest.id(),
                        ErrorCode.DEFECT_NOT_FOUND
                ));
    }
}
