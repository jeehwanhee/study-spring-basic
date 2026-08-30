package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum WeatherErrorCode implements CustomErrorCode {
    WEATHER_API_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "날씨 정보를 가져올 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;

    WeatherErrorCode(HttpStatus httpStatus, String message) {
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
