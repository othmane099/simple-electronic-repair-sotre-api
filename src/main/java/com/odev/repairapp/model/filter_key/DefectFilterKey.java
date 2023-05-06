package com.odev.repairapp.model.filter_key;

public enum DefectFilterKey implements FilterKey{
    TITLE("title"), PRICE("price"), COST("COST"), CREATED_AT("createdAt");

    private final String val;

    DefectFilterKey(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }
}
