package com.blog.common.exception;

/**
 * 修改数据时的错误
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 11:11
 */
public class UpdateConfException extends RuntimeException{
    public UpdateConfException() {
    }

    public UpdateConfException(String message) {
        super(message);
    }

    public UpdateConfException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateConfException(Throwable cause) {
        super(cause);
    }

    public UpdateConfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
