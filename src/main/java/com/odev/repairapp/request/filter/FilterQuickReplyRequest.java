package com.odev.repairapp.request.filter;

import com.odev.repairapp.model.filter_key.FilterKey;
import com.odev.repairapp.model.filter_key.QuickReplyFilterKey;
import com.odev.repairapp.model.SortingType;

public record FilterQuickReplyRequest(String keyword, QuickReplyFilterKey filterKey, SortingType sorting) implements FilterRequest {
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
