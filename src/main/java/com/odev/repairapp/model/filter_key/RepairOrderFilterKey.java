package com.odev.repairapp.model.filter_key;

public enum RepairOrderFilterKey implements FilterKey{
    TRACKING("tracking"),
    CUSTOMER_NAME("name"),
    CUSTOMER_PHONE("phone"),
    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt");
    private final String val;

    RepairOrderFilterKey(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }
}
