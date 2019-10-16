package com.example.mycommunity.exception;

public enum  CustomizeErrorCode implements InCustomizeErrorCode {


    QUESTION_NOT_FOUND(2001, "问题不存在"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中问题或者评论进行回复"),
    NO_LOGIN(2003, "未登录不能评论"),
    SYS_ERROR(2004, "服务器异常，请稍后再试"),
    TYPE_PARAM_WRONG(2005, "评论类型不存在"),
    COMMENT_NOT_FOUND(2005, "回复的评论不存在");

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
    private String message;
    private Integer code;
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
