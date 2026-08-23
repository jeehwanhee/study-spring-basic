package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum QuestErrorCode implements CustomErrorCode {

    ALREADY_COMPLETED(HttpStatus.CONFLICT,"이미 완료된 퀘스트입니다"),
    INVALID_TITLE(HttpStatus.BAD_REQUEST, "제목이 없습니다"),
    INVALID_DETAIL(HttpStatus.BAD_REQUEST, "상세내용은 2자이상 100자 이하입니다"),
    QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 퀘스트를 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;

    QuestErrorCode(HttpStatus httpStatus, String message) {
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
