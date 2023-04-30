package com.odev.repairapp.request.filter;

import com.odev.repairapp.model.SortingType;
import com.odev.repairapp.model.filter_key.FilterKey;

public interface FilterRequest {
    String getKeyword();
    FilterKey getFilterKey();
    SortingType getSorting();
}
