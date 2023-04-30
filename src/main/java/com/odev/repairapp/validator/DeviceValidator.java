package com.odev.repairapp.validator;

import com.odev.repairapp.request.DeviceRequest;
import com.odev.repairapp.request.DeviceWithIdRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DeviceValidator {

    public static List<String> validate(DeviceRequest request){
        List<String> errors = new ArrayList<>();

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Device's name should not be null or empty");
        }

        if (request.brand() == null || request.brand().id() == null){
            errors.add("Device's name and Device's ID should not be null");
        }

        return errors;
    }

    public static List<String> validate(DeviceWithIdRequest request){
        List<String> errors = new ArrayList<>();

        if (request.id() == null)
            errors.add("Device's ID should not be null");

        if (request.name() == null || !StringUtils.hasLength(request.name())){
            errors.add("Device's name should not be null or empty");
        }

        if (request.brand() == null || request.brand().id() == null){
            errors.add("Device's name and Device's ID should not be null");
        }

        return errors;
    }
}
