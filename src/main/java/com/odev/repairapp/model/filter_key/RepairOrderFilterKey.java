package com.odev.repairapp.model.filter_key;

public enum RepairOrderFilterKey implements FilterKey{
    TRACKING("tracking"),
    STATUS("status"),
    PRIORITY("priority"),
    TECHNICIAN("technician"),
    CREATED_AT("createdAt"),
    ORDER_NUMBER("orderNumber"),
    UPDATED_AT("updatedAt"),
    CLOSED_AT("closedAt");

    private final String val;

    RepairOrderFilterKey(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }
}
