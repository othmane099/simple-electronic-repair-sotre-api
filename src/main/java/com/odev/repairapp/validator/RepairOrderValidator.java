package com.odev.repairapp.validator;

import com.odev.repairapp.request.RepairOrderRequest;
import com.odev.repairapp.request.RepairOrderStatusRequest;
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

        if (request.repairPriorityId() == null)
            errors.add("Order's priorityId should not be null");

        if (request.deviceId() == null)
            errors.add("Order's deviceId should not be null");

        if (request.repairStatusId() == null)
            errors.add("Order's statusId should not be null");

        if (request.defectsIds() == null)
            errors.add("Order's defects should not be null");

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

        if (request.repairPriorityId() == null)
            errors.add("Order's priorityId should not be null");

        if (request.deviceId() == null)
            errors.add("Order's deviceId should not be null");

        if (request.repairStatusId() == null)
            errors.add("Order's statusId should not be null");

        if (request.defectsIds() == null)
            errors.add("Order's defects should not be null");

        return errors;
    }

    public static List<String> validate(RepairOrderStatusRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null)
            errors.add("RepairOrder's ID should not be null");

        if (request.statusId() == null){
            errors.add("RepairOrder's status should not be null");
        }

        return errors;
    }
}
