package com.odev.repairapp.validator;

import com.odev.repairapp.request.QuickReplyRequest;
import com.odev.repairapp.request.QuickReplyWithIdRequest;
import com.odev.repairapp.request.RepairPriorityRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QuickReplyValidator {

    public static List<String> validate(QuickReplyRequest request){
        List<String> errors = new ArrayList<>();

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Repair priority's name should not be null or empty");
        }

        if (request.body() == null || !StringUtils.hasLength(request.body())){
            errors.add("Repair priority's body should not be null or empty");
        }

        return errors;
    }

    public static List<String> validate(QuickReplyWithIdRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null)
            errors.add("Repair priority's ID should not be null");

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Repair priority's name should not be null or empty");
        }

        if (request.body() == null || !StringUtils.hasLength(request.body())){
            errors.add("Repair priority's body should not be null or empty");
        }

        return errors;
    }
}
