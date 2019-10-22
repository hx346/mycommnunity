package com.example.mycommunity.exception;

public enum  CustomizeErrorCode implements InCustomizeErrorCode {


    QUESTION_NOT_FOUND(2001, "问题不存在"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中问题或者评论进行回复"),
    NO_LOGIN(2003, "未登录不能评论"),
    SYS_ERROR(2004, "服务器异常，请稍后再试"),
    TYPE_PARAM_WRONG(2005, "评论类型不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在"),
    COMMENT_IS_EMPTY(2007, "回复的评论不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009, "消息莫非是不翼而飞了？"),
    FILE_UPLOAD_FAIL(2010, "图片上传失败"),
    INVALID_INPUT(2011, "非法输入"),
    INVALID_OPERATION(2012, "兄弟，是不是走错房间了？"),
    ;

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
