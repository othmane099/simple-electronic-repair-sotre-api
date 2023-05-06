package com.odev.repairapp.validator;

import com.odev.repairapp.request.DefectRequest;
import com.odev.repairapp.request.DefectWithIdRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DefectValidator {

    public static List<String> validate(DefectRequest request){
        List<String> errors = new ArrayList<>();

        if (request.title() == null || !StringUtils.hasLength(request.title())){
            errors.add("Defect's title should not be null or empty");
        }

        if (request.requiredTime() == null || !StringUtils.hasLength(request.requiredTime())){
            errors.add("Defect's required time should not be null or empty");
        }

        if (request.device() == null || request.device().id() == null){
            errors.add("Defect's device and device's ID should not be null");
        }

        return errors;
    }

    public static List<String> validate(DefectWithIdRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null)
            errors.add("Defect's ID should not be null");

        if (request.title() == null || !StringUtils.hasLength(request.title())){
            errors.add("Defect's title should not be null or empty");
        }

        if (request.requiredTime() == null || !StringUtils.hasLength(request.requiredTime())){
            errors.add("Defect's required time should not be null or empty");
        }

        if (request.device() == null || request.device().id() == null){
            errors.add("Defect's device and device's ID should not be null");
        }

        return errors;
    }
}
