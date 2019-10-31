package com.blog.common.exception;

/**
 * 输出参数时可能出现的异常
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 10:31
 */
public class ConfOutputException extends RuntimeException {
    public ConfOutputException() {
    }

    public ConfOutputException(String message) {
        super(message);
    }

    public ConfOutputException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfOutputException(Throwable cause) {
        super(cause);
    }

    public ConfOutputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
