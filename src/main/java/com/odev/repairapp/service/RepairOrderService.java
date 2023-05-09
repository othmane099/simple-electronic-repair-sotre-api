package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.*;
import com.odev.repairapp.repository.*;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.RepairOrderRequest;
import com.odev.repairapp.request.RepairOrderWithIdRequest;
import com.odev.repairapp.request.filter.FilterRepairOrderRequest;
import com.odev.repairapp.response.RepairOrderResponse;
import com.odev.repairapp.utils.CriteriaFilter;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.utils.Helper;
import com.odev.repairapp.validator.RepairOrderValidator;
import com.odev.repairapp.validator.filter.FilterDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.odev.repairapp.model.filter_key.RepairOrderFilterKey.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RepairOrderService {

    private final CriteriaFilter<RepairOrder> criteriaFilter;
    private final RepairOrderRepository repository;
    private final RepairPriorityRepository repairPriorityRepository;
    private final DeviceRepository deviceRepository;
    private final DefectRepository defectRepository;
    private final RepairStatusRepository repairStatusRepository;
    public Page<RepairOrderResponse> findAll(int pageNum, int pageSize, FilterRepairOrderRequest request) {
        List<String> errors = FilterDataValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Repair order filter is not valid",
                    ErrorCode.REPAIR_ORDER_NOT_VALID,
                    errors);

        Pageable pageable = PageRequest.of(
                pageNum,
                pageSize,
                Sort.by(
                        Sort.Direction.fromString(request.sorting().name().toLowerCase()),
                        request.getFilterKey().getValue()
                )
        );

        String keyword = request.keyword();
        Specification<RepairOrder> specification = criteriaFilter.contains(TRACKING.getValue(), keyword)
                .or(criteriaFilter.contains(CUSTOMER_PHONE.getValue(), keyword))
                .or(criteriaFilter.contains(CUSTOMER_NAME.getValue(), keyword))
                .or(criteriaFilter.hasStatus("repairStatus", keyword));

    return repository.findAll(specification, pageable)
            .map(RepairOrderResponse::toResponse);
    }

    public RepairOrderResponse save(RepairOrderRequest request){

        List<String> errors = RepairOrderValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException(
                    "Repair order is not valid",
                    ErrorCode.REPAIR_ORDER_NOT_VALID,
                    errors
            );

        RepairPriority repairPriority = repairPriorityRepository
                .findById(request.repairPriorityId())
                .orElseThrow(() -> new RepairAppException("Repair priority not found", ErrorCode.REPAIR_PRIORITY_NOT_FOUND));

        Device device = deviceRepository
                .findById(request.repairPriorityId())
                .orElseThrow(() -> new RepairAppException("Device not found", ErrorCode.DEVICE_NOT_FOUND));

        RepairStatus repairStatus = repairStatusRepository.findById(request.repairStatusId())
                .orElseThrow(() -> new RepairAppException("Status not found", ErrorCode.REPAIR_STATUS_NOT_FOUND));

        List<Defect> defects = new ArrayList<>();
        request.defectsIds().forEach(id -> {
            Defect defect = defectRepository.findById(id)
                    .orElseThrow(() -> new RepairAppException("Defect not found", ErrorCode.DEFECT_NOT_FOUND));
            defects.add(defect);
        });


        RepairOrder repairOrder = RepairOrderRequest.toEntity(request);
        repairOrder.setUuid(UUID.randomUUID());
        repairOrder.setTracking(String.valueOf(System.currentTimeMillis()));
        repairOrder.setRepairPriority(repairPriority);
        repairOrder.setDefects(defects);
        repairOrder.setDevice(device);
        repairOrder.setRepairStatus(repairStatus);

        if (request.prePaid() != 0){
            double due = request.totalCharges() - request.prePaid();
            repairOrder.setPaymentStatus( due < 1);
        }

        RepairOrder repairOrderSaved = repository.save(repairOrder);
        return RepairOrderResponse.toResponse(repairOrderSaved);
    }

    public RepairOrderResponse update(RepairOrderWithIdRequest request){
        List<String> errors = RepairOrderValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Repair order is not valid", ErrorCode.REPAIR_ORDER_NOT_VALID, errors);

        // Check if object already exists in database
        Optional<RepairOrder> optionalRepairOrder = repository.findById(request.id());
        if (optionalRepairOrder.isEmpty())
            throw new RepairAppException(
                    "RepairOrder is not found",
                    ErrorCode.REPAIR_ORDER_NOT_FOUND
            );

        RepairPriority repairPriority = repairPriorityRepository
                .findById(request.repairPriorityId())
                .orElseThrow(() -> new RepairAppException("Repair priority not found", ErrorCode.REPAIR_PRIORITY_NOT_FOUND));

        RepairStatus repairStatus = repairStatusRepository.findById(request.repairStatusId())
                .orElseThrow(() -> new RepairAppException("Status not found", ErrorCode.REPAIR_STATUS_NOT_FOUND));


        Device device = deviceRepository
                .findById(request.repairPriorityId())
                .orElseThrow(() -> new RepairAppException("Device not found", ErrorCode.DEVICE_NOT_FOUND));

        List<Defect> defects = new ArrayList<>();
        request.defectsIds().forEach(id -> {
            Defect defect = defectRepository.findById(id)
                    .orElseThrow(() -> new RepairAppException("Defect not found", ErrorCode.DEFECT_NOT_FOUND));
            defects.add(defect);
        });


        RepairOrder repairOrder = RepairOrderWithIdRequest.toEntity(request);
        repairOrder.setUuid(optionalRepairOrder.get().getUuid());
        repairOrder.setRepairStatus(optionalRepairOrder.get().getRepairStatus());
        repairOrder.setRepairStatus(repairStatus);
        repairOrder.setRepairPriority(repairPriority);
        repairOrder.setDefects(defects);
        repairOrder.setDevice(device);

        repairOrder = repository.saveAndFlush(repairOrder);
        return RepairOrderResponse.toResponse(repairOrder);
    }

    public void deleteById(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        RepairOrder repairOrder = myFindById(idRequest);
        repository.deleteById(idRequest.id());
    }

    public RepairOrderResponse show(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        RepairOrder repairOrder = myFindById(idRequest);
        return RepairOrderResponse.toResponse(repairOrder);

    }

    private RepairOrder myFindById(IdRequest idRequest){
        return repository
                .findById(idRequest.id())
                .orElseThrow(() -> new RepairAppException(
                        "No data found with ID = "+idRequest.id(),
                        ErrorCode.REPAIR_ORDER_NOT_FOUND
                ));
    }
}
