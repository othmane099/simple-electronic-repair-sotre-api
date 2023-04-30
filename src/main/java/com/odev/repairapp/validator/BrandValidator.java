package com.odev.repairapp.validator;

import com.odev.repairapp.request.BrandRequest;
import com.odev.repairapp.request.BrandWithIdRequest;
import com.odev.repairapp.request.RepairPriorityRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BrandValidator {

    public static List<String> validate(BrandRequest request){
        List<String> errors = new ArrayList<>();

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Brand's name should not be null or empty");
        }

        return errors;
    }

    public static List<String> validate(BrandWithIdRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null)
            errors.add("Brand's ID should not be null");

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Brand's name should not be null or empty");
        }

        return errors;
    }
}
