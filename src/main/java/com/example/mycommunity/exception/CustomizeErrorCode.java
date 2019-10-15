package com.example.mycommunity.exception;

public enum  CustomizeErrorCode implements InCustomizeErrorCode {


    QUESTION_NOT_FOUND("问题不存在");

    CustomizeErrorCode(String message) {
        this.message = message;
    }
    private String message;
    public String getMessage() {
        return message;
    }
}
