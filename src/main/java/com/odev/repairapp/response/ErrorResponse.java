package com.odev.repairapp.response;

import com.odev.repairapp.utils.ErrorCode;
import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(
        Integer httpCode,
        ErrorCode errorCode,
        String message,
        List<String> errors
) {}
