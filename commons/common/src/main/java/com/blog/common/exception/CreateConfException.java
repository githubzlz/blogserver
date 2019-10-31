package com.blog.common.exception;

/**
 * 创建数据时发生的异常
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 10:53
 */
public class CreateConfException extends RuntimeException {

    public CreateConfException() {
    }

    public CreateConfException(String message) {
        super(message);
    }

    public CreateConfException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateConfException(Throwable cause) {
        super(cause);
    }

    public CreateConfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
