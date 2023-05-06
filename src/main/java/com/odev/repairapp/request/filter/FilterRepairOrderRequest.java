package com.odev.repairapp.request.filter;

import com.odev.repairapp.model.SortingType;
import com.odev.repairapp.model.filter_key.DeviceFilterKey;
import com.odev.repairapp.model.filter_key.FilterKey;

public record FilterRepairOrderRequest(String keyword, DeviceFilterKey filterKey, SortingType sorting) implements FilterRequest{
    @Override
    public String getKeyword() {
        return this.keyword();
    }

    @Override
    public FilterKey getFilterKey() {
        return this.filterKey();
    }

    @Override
    public SortingType getSorting() {
        return this.sorting();
    }
}
