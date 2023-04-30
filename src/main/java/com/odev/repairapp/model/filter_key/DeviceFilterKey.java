package com.odev.repairapp.model.filter_key;

public enum DeviceFilterKey implements FilterKey{
    NAME("name"), MODEL("updatedAt") , CREATED_AT("createdAt");

    private final String val;

    DeviceFilterKey(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }
}
