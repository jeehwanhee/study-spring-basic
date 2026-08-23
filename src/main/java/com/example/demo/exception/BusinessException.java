package com.example.demo.exception;

public class BusinessException extends RuntimeException{

    private final CustomErrorCode errorCode;

    public BusinessException(CustomErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomErrorCode getErrorCode() {
        return errorCode;
    }
}
