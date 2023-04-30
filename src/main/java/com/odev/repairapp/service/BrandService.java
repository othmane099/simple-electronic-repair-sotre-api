package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.Brand;
import com.odev.repairapp.model.filter_key.QuickReplyFilterKey;
import com.odev.repairapp.repository.BrandRepository;
import com.odev.repairapp.request.BrandRequest;
import com.odev.repairapp.request.BrandWithIdRequest;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.response.BrandResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.utils.Helper;
import com.odev.repairapp.validator.BrandValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public List<BrandResponse> findAll(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, QuickReplyFilterKey.NAME.getValue()))
                .stream()
                .map(BrandResponse::toResponse)
                .collect(Collectors.toList());
    }


    public BrandResponse save(BrandRequest request){
        // Validate the object
        List<String> errors = BrandValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Brand is not valid", ErrorCode.BRAND_NOT_VALID, errors);
        // Check if object already exists in database
        Optional<Brand> optionalBrand = repository.findByName(request.name());
        if (optionalBrand.isPresent())
            throw new RepairAppException(
                    "Brand's name should be unique",
                    ErrorCode.BRAND_ALREADY_IN_USE
            );

        Brand brand = repository.save(BrandRequest.toEntity(request));
        return BrandResponse.toResponse(brand);
    }

    public BrandResponse update(BrandWithIdRequest request){

        List<String> errors = BrandValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("Brand is not valid", ErrorCode.BRAND_NOT_VALID, errors);

        // Check if object already exists in database
        Optional<Brand> optionalBrand = repository.findById(request.id());
        if (optionalBrand.isEmpty())
            throw new RepairAppException(
                    "Brand is not found",
                    ErrorCode.BRAND_NOT_FOUND
            );

        Brand brand = repository.save(BrandWithIdRequest.toEntity(request));
        return BrandResponse.toResponse(brand);
    }

    public void deleteById(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);

        Brand brand = myFindById(idRequest);

        if (brand.beingUsed())
            throw new RepairAppException(
                    "Unable to delete data is being used",
                    ErrorCode.BRAND_IS_RELATED_TO_EXISTING_DEVICE
            );
        repository.deleteById(idRequest.id());
    }

    public BrandResponse show(IdRequest idRequest){
        Helper.hCheckIfIdNotNull(idRequest);
        Brand brand = myFindById(idRequest);
        return BrandResponse.toResponse(brand);

    }

    private Brand myFindById(IdRequest idRequest){
        return repository
                .findById(idRequest.id())
                .orElseThrow(() -> new RepairAppException(
                        "No data found with ID = "+idRequest.id(),
                        ErrorCode.BRAND_NOT_FOUND
                ));
    }

}
