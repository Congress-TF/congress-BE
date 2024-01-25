package com.congress.commonmodule.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;
    private final int errorCode;

    private ErrorResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public static ErrorResponse from(BusinessException exception){
        return new ErrorResponse(exception.getMessage(), exception.getErrorCode());
    }
}
