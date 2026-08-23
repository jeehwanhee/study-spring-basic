package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public interface CustomErrorCode {
    HttpStatus getHttpStatus();
    String getMessage();
}
