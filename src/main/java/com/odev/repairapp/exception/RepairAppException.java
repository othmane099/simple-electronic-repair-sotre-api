package com.odev.repairapp.exception;

import com.odev.repairapp.utils.ErrorCode;
import lombok.Getter;

import java.util.List;

@Getter
public class RepairAppException extends RuntimeException{

    private ErrorCode errorCode;
    private List<String> errors;

    public RepairAppException(String message){
        super(message);
    }

    public RepairAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepairAppException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public RepairAppException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorCode = errorCode;
    }

    public RepairAppException(String message, ErrorCode errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }
}
