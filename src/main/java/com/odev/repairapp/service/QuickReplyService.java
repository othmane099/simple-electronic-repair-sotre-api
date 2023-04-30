package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.QuickReply;
import com.odev.repairapp.model.filter_key.QuickReplyFilterKey;
import com.odev.repairapp.repository.QuickReplyRepository;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.request.QuickReplyRequest;
import com.odev.repairapp.request.QuickReplyWithIdRequest;
import com.odev.repairapp.request.filter.FilterQuickReplyRequest;
import com.odev.repairapp.response.QuickReplyResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.utils.Helper;
import com.odev.repairapp.validator.QuickReplyValidator;
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
public class QuickReplyService {
    private final QuickReplyRepository repository;

    public Page<QuickReplyResponse> findAll(int pageNum, int pageSize, FilterQuickReplyRequest request) {
        List<String> errors = FilterDataValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Quick reply's filter is not valid",
                    ErrorCode.FILTER_DATA_IS_NOT_VALID,
                    errors);

        Pageable pageable = PageRequest.of(
                pageNum,
                pageSize,
                Sort.by(
                        Sort.Direction.fromString(request.getSorting().name().toLowerCase()),
                        request.getFilterKey().getValue()
                )
        );

        return repository.findAll(
                (root, cq, cb) -> cb.like(
                        root.get(QuickReplyFilterKey.NAME.getValue()),
                        "%" + request.getKeyword() + "%"
                ),
                pageable
        ).map(QuickReplyResponse::toResponse);
    }

    public QuickReplyResponse save(QuickReplyRequest request){

        List<String> errors = QuickReplyValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException(
                    "Quick reply is not valid",
                    ErrorCode.QUICK_REPLY_NOT_VALID,
                    errors
            );

        QuickReply quickReply = repository.save(QuickReplyRequest.toEntity(request));
        return QuickReplyResponse.toResponse(quickReply);
    }

    public QuickReplyResponse update(QuickReplyWithIdRequest request){
        List<String> errors = QuickReplyValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Quick reply is not valid", ErrorCode.QUICK_REPLY_NOT_VALID, errors);

        // Check if object already exists in database
        Optional<QuickReply> optionalQuickReply = repository.findById(request.id());
        if (optionalQuickReply.isEmpty())
            throw new RepairAppException(
                    "Quick reply is not found",
                    ErrorCode.QUICK_REPLY_NOT_FOUND
            );

        QuickReply quickReply = repository.save(QuickReplyWithIdRequest.toEntity(request));
        return QuickReplyResponse.toResponse(quickReply);
    }

    public void deleteById(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        myFindById(idRequest);
        repository.deleteById(idRequest.id());
    }

    public QuickReplyResponse show(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        QuickReply quickReply = myFindById(idRequest);
        return QuickReplyResponse.toResponse(quickReply);

    }

    private QuickReply myFindById(IdRequest idRequest){
        return repository
                .findById(idRequest.id())
                .orElseThrow(() -> new RepairAppException(
                        "No data found with ID = "+idRequest.id(),
                        ErrorCode.QUICK_REPLY_NOT_FOUND
                ));
    }




}
