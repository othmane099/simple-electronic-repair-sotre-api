package com.odev.repairapp.handler;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RepairAppException.class)
    public ResponseEntity<ErrorResponse> handleException(RepairAppException exception) {
        final HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .httpCode(internalServerError.value())
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorResponse, internalServerError);
    }
}
