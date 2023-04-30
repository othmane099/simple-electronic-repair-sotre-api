package com.odev.repairapp.validator;

import com.odev.repairapp.request.RepairStatusRequest;
import com.odev.repairapp.request.RepairStatusWithIdRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RepairStatusValidator {

    public static List<String> validate(RepairStatusRequest request){
        List<String> errors = new ArrayList<>();

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Repair status's name should not be null or empty");
        }

        return errors;
    }

    public static List<String> validate(RepairStatusWithIdRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null){
            errors.add("Repair status's ID should not be null");
        }

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Repair status's name should not be null or empty");
        }

        return errors;
    }
}
