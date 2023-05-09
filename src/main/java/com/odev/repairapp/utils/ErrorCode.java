package com.odev.repairapp.utils;

public enum ErrorCode {

    NULL_ID(1000),
    DATA_NOT_FOUND(1001),

    USER_NOT_VALID(1900),
    REPAIR_PRIORITY_NOT_FOUND(2000),
    REPAIR_PRIORITY_NOT_VALID(2001),
    REPAIR_PRIORITY_ALREADY_IN_USE(2002),
    REPAIR_PRIORITY_IS_RELATED_TO_EXISTING_REPAIR_ORDER(2003),
    QUICK_REPLY_NOT_FOUND(2004),
    QUICK_REPLY_NOT_VALID(2005),
    BRAND_NOT_FOUND(2007),
    BRAND_NOT_VALID(2008),
    BRAND_ALREADY_IN_USE(2009),
    BRAND_IS_RELATED_TO_EXISTING_DEVICE(2010),
    REPAIR_STATUS_NOT_VALID(2013),
    REPAIR_STATUS_ALREADY_IN_USE(2014),
    REPAIR_STATUS_IS_RELATED_TO_EXISTING_REPAIR_ORDER(2015),
    REPAIR_STATUS_NOT_FOUND(2016),

    DEVICE_NOT_VALID(2019),
    DEVICE_ALREADY_IN_USE(2020),
    DEVICE_IS_RELATED_TO_EXISTING_DEFECT_OR_REPAIR_ORDER(2021),
    DEVICE_NOT_FOUND(2022),

    DEFECT_NOT_VALID(2025),
    DEFECT_ALREADY_IN_USE(2026),
    DEFECT_IS_RELATED_TO_EXISTING_DEFECT_OR_REPAIR_ORDER(2027),
    DEFECT_NOT_FOUND(2028),

    REPAIR_ORDER_NOT_VALID(2032),
    REPAIR_ORDER_NOT_FOUND(2033),

    FILTER_DATA_IS_NOT_VALID(4000);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
