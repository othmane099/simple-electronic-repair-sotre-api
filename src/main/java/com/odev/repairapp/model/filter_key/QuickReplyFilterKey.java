package com.odev.repairapp.model.filter_key;

import java.util.logging.Filter;

public enum QuickReplyFilterKey implements FilterKey {
    NAME("name"), CREATED_AT("createdAt"), UPDATED_AT("updatedAt");

    private final String val;

    QuickReplyFilterKey(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }
}
