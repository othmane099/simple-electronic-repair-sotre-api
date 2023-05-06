package com.odev.repairapp.validator;

import com.odev.repairapp.request.RepairOrderRequest;
import com.odev.repairapp.request.RepairOrderWithIdRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RepairOrderValidator {

    public static List<String> validate(RepairOrderRequest request){
        List<String> errors = new ArrayList<>();

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Customer's name should not be null or empty");
        }

        if (request.email() == null || !StringUtils.hasLength(request.email())){
            errors.add("Customer's name should not be null or empty");
        }

        return errors;
    }

    public static List<String> validate(RepairOrderWithIdRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null)
            errors.add("RepairOrder's ID should not be null");

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Customer's name should not be null or empty");
        }

        if (request.email() == null || !StringUtils.hasLength(request.email())){
            errors.add("Customer's name should not be null or empty");
        }

        return errors;
    }
}
