package com.odev.repairapp.utils;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.validator.ObjectIdValidator;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static void hCheckIfIdNotNull(IdRequest idRequest) throws RepairAppException {
        List<String> errors = new ArrayList<>();
        String validationMessage = ObjectIdValidator.validate(idRequest);
        if (StringUtils.hasLength(validationMessage)) {
            errors.add(validationMessage);
            throw new RepairAppException(
                    "ID should not be null",
                    ErrorCode.NULL_ID,
                    errors);
        }
    }

}
