package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.RepairStatus;
import com.odev.repairapp.model.filter_key.QuickReplyFilterKey;
import com.odev.repairapp.repository.RepairOrderRepository;
import com.odev.repairapp.repository.RepairStatusRepository;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.RepairStatusRequest;
import com.odev.repairapp.request.RepairStatusWithIdRequest;
import com.odev.repairapp.response.RepairStatusResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.utils.Helper;
import com.odev.repairapp.validator.RepairStatusValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RepairStatusService {

    private final RepairStatusRepository repository;
    private final RepairOrderRepository repairOrderRepository;

    public List<RepairStatusResponse> findAll(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, QuickReplyFilterKey.NAME.getValue()))
                .stream()
                .map(RepairStatusResponse::toResponse)
                .collect(Collectors.toList());
    }


    public RepairStatusResponse save(RepairStatusRequest request){
        // Validate the object
        List<String> errors = RepairStatusValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Repair status is not valid", ErrorCode.REPAIR_STATUS_NOT_VALID, errors);
        // Check if object already exists in database
        Optional<RepairStatus> optionalRepairStatus = repository.findByName(request.name());
        if (optionalRepairStatus.isPresent())
            throw new RepairAppException(
                    "Repair status's name should be unique",
                    ErrorCode.REPAIR_STATUS_ALREADY_IN_USE
            );

        RepairStatus repairStatus = repository.save(RepairStatusRequest.toEntity(request));
        return RepairStatusResponse.toResponse(repairStatus);
    }

    public RepairStatusResponse update(RepairStatusWithIdRequest request){
        List<String> errors = RepairStatusValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Repair status is not valid", ErrorCode.REPAIR_STATUS_NOT_VALID, errors);

        // Check if object already exists in database
        Optional<RepairStatus> optionalRepairStatus = repository.findById(request.id());
        if (optionalRepairStatus.isEmpty())
            throw new RepairAppException(
                    "Repair status is not found",
                    ErrorCode.REPAIR_STATUS_NOT_FOUND
            );

        RepairStatus repairStatus = repository.save(RepairStatusWithIdRequest.toEntity(request));
        return RepairStatusResponse.toResponse(repairStatus);
    }

    public void deleteById(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);

        RepairStatus repairStatus = myFindById(idRequest);

        if (repairOrderRepository.countByRepairStatus(repairStatus) > 0)
            throw new RepairAppException(
                    "Unable to delete data is being used",
                    ErrorCode.REPAIR_STATUS_IS_RELATED_TO_EXISTING_REPAIR_ORDER
            );
        repository.deleteById(idRequest.id());
    }

    public RepairStatusResponse show(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        RepairStatus repairStatus = myFindById(idRequest);
        return RepairStatusResponse.toResponse(repairStatus);

    }

    private RepairStatus myFindById(IdRequest idRequest){
        return repository
                .findById(idRequest.id())
                .orElseThrow(() -> new RepairAppException(
                        "No data found with ID = "+idRequest.id(),
                        ErrorCode.REPAIR_STATUS_NOT_FOUND
                ));
    }

}
