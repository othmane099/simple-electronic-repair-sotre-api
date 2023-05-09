package com.odev.repairapp.validator;

import com.odev.repairapp.request.BrandRequest;
import com.odev.repairapp.request.BrandWithIdRequest;
import com.odev.repairapp.request.RegisterRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationValidator {

    public static List<String> validate(RegisterRequest request){
        List<String> errors = new ArrayList<>();

        if (request.email() == null || !StringUtils.hasLength(request.email())){
            errors.add("User's email should not be null or empty");
        }

        if (request.password() == null || !StringUtils.hasLength(request.password())){
            errors.add("User's password should not be null or empty");
        }

        if (request.authorityList() == null){
            errors.add("User's authorities should not be null");
        }

        return errors;
    }

}
