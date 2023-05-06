package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.RepairOrder;
import com.odev.repairapp.model.filter_key.RepairOrderFilterKey;
import com.odev.repairapp.repository.RepairOrderRepository;
import com.odev.repairapp.request.RepairOrderWithIdRequest;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.RepairOrderRequest;
import com.odev.repairapp.request.filter.FilterRepairOrderRequest;
import com.odev.repairapp.response.RepairOrderResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.utils.Helper;
import com.odev.repairapp.validator.RepairOrderValidator;
import com.odev.repairapp.validator.filter.FilterDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RepairOrderService {

    private final RepairOrderRepository repository;
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

        return repository.findAll(
                (root, cq, cb) -> cb.like(
                        root.get(RepairOrderFilterKey.TRACKING.getValue()),
                        "%" + request.keyword() + "%"
                ),
                pageable
        ).map(RepairOrderResponse::toResponse);
    }

    public RepairOrderResponse save(RepairOrderRequest request){

        List<String> errors = RepairOrderValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException(
                    "Repair order is not valid",
                    ErrorCode.REPAIR_ORDER_NOT_VALID,
                    errors
            );

        RepairOrder repairOrder = RepairOrderRequest.toEntity(request);

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

        RepairOrder repairOrder = repository.save(RepairOrderWithIdRequest.toEntity(request));
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
