package com.odev.repairapp.validator;

import com.odev.repairapp.request.RepairPriorityRequest;
import com.odev.repairapp.request.RepairPriorityWithIdRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RepairPriorityValidator {

    public static List<String> validate(RepairPriorityRequest request){
        List<String> errors = new ArrayList<>();

        if (request.priorityValue() == null){
            errors.add("Repair priority's value should not be null");
        }

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Repair priority's name should not be null or empty");
        }

        return errors;
    }

    public static List<String> validate(RepairPriorityWithIdRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null){
            errors.add("Repair priority's ID should not be null");
        }

        if (request.priorityValue() == null){
            errors.add("Repair priority's value should not be null");
        }

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Repair priority's name should not be null or empty");
        }

        return errors;
    }
}
