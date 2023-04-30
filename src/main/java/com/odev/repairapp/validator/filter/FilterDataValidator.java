package com.odev.repairapp.validator.filter;

import com.odev.repairapp.request.filter.FilterRequest;

import java.util.ArrayList;
import java.util.List;

public class FilterDataValidator {

    public static List<String> validate(FilterRequest request){
        List<String> errors = new ArrayList<>();
        if (request.getKeyword() == null)
            errors.add("Search keyword should not be null");

        if (request.getFilterKey() == null)
            errors.add("Filter's value should not be null");

        if (request.getSorting() == null)
            errors.add("Sort's value should not be null");

        return errors;
    }
}
