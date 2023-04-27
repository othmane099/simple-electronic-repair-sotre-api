package com.odev.repairapp.utils;

public enum ErrorCode {

    NULL_ID(1000),


    REPAIR_PRIORITY_NOT_FOUND(2000),
    REPAIR_PRIORITY_NOT_VALID(2001),
    REPAIR_PRIORITY_ALREADY_IN_USE(2002),
    REPAIR_PRIORITY_IS_RELATED_TO_EXISTING_REPAIR_ORDER(2003)
    ;

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
