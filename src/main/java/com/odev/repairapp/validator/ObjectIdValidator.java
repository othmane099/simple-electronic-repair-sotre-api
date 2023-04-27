package com.odev.repairapp.validator;

import com.odev.repairapp.request.IdRequest;
import com.odev.repairapp.utils.Constant;

import java.util.List;

public class ObjectIdValidator {

    public static String validate(IdRequest idRequest){
        if (idRequest == null)
            return "Object's ID should not be null";

        return Constant.EMPTY_STRING;
    }
}
