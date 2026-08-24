package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum UserErrorCode implements CustomErrorCode {

    DUPLICATE_USERNAME(HttpStatus.CONFLICT,"이미 사용중인 아이디"),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED,"아이디 또는 비밀번호가 일치하지 않습니다"),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED,"아이디 또는 비밀번호가 일치하지 않습니다"),
    INVALID_USERNAME(HttpStatus.BAD_REQUEST, "아이디가 없습니다"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 없습니다"),
    INVALID_PASSWORD_LENGTH(HttpStatus.BAD_REQUEST, "비밀번호는 최소 4자 이상이어야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;

    UserErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }


    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
