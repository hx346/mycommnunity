package com.example.mycommunity.exception;

public class CustomizeException extends RuntimeException {
    private  String message;

    public CustomizeException(InCustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }


    @Override
    public String getMessage() {
        return message;
    }


}
