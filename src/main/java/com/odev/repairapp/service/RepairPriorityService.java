package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.RepairPriority;
import com.odev.repairapp.repository.RepairOrderRepository;
import com.odev.repairapp.repository.RepairPriorityRepository;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.RepairPriorityRequest;
import com.odev.repairapp.request.RepairPriorityWithIdRequest;
import com.odev.repairapp.response.RepairPriorityResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.validator.ObjectIdValidator;
import com.odev.repairapp.validator.RepairPriorityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RepairPriorityService {
    private final RepairPriorityRepository repository;
    private final RepairOrderRepository repairOrderRepository;

    public List<RepairPriorityResponse> findAll(){
        return repository
                .findAll()
                .stream()
                .map(RepairPriorityResponse::toResponse)
                .collect(Collectors.toList());
    }


    public RepairPriorityResponse save(RepairPriorityRequest request){
        // Validate the object
        List<String> errors = RepairPriorityValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Repair priority is not valid", ErrorCode.REPAIR_PRIORITY_NOT_VALID, errors);
        // Check if object already exists in database
        Optional<RepairPriority> optionalRepairPriority = repository.findByName(request.name());
        if (optionalRepairPriority.isPresent())
            throw new RepairAppException(
                        "Repair priority's name should be unique",
                        ErrorCode.REPAIR_PRIORITY_ALREADY_IN_USE
                );

        RepairPriority repairPriority = repository.save(RepairPriorityRequest.toEntity(request));
        return RepairPriorityResponse.toResponse(repairPriority);
    }

    public RepairPriorityResponse update(RepairPriorityWithIdRequest request){
        RepairPriority repairPriority = repository.save(RepairPriorityWithIdRequest.toEntity(request));
        return RepairPriorityResponse.toResponse(repairPriority);
    }

    public void deleteById(IdRequest idRequest){
        checkIfIdNotNull(idRequest);

        RepairPriority repairPriority = findById(idRequest);

        if (repairOrderRepository.countByRepairPriority(repairPriority) > 0)
            throw new RepairAppException(
                    "Unable to delete data is being used",
                    ErrorCode.REPAIR_PRIORITY_IS_RELATED_TO_EXISTING_REPAIR_ORDER
            );
        repository.deleteById(idRequest.id());
    }

    public RepairPriorityResponse show(IdRequest idRequest){
        checkIfIdNotNull(idRequest);
        RepairPriority repairPriority = findById(idRequest);
        return RepairPriorityResponse.toResponse(repairPriority);

    }

    private void checkIfIdNotNull(IdRequest idRequest) throws RepairAppException{
        List<String> errors = new ArrayList<>();
        String validationMessage = ObjectIdValidator.validate(idRequest);
        if (StringUtils.hasLength(validationMessage)) {
            errors.add(validationMessage);
            throw new RepairAppException(
                    "Repair priority's ID should not be null",
                    ErrorCode.NULL_ID,
                    errors);
        }
    }

    private RepairPriority findById(IdRequest idRequest){
        return repository
                .findById(idRequest.id())
                .orElseThrow(() -> new RepairAppException(
                        "No data found with ID = "+idRequest.id(),
                        ErrorCode.REPAIR_PRIORITY_NOT_FOUND
                ));
    }
}
