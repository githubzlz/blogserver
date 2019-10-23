package com.blog.common.exception;

/**
 * 输入错误的参数时抛出的异常
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 9:59
 */
public class ConfInputException extends RuntimeException {
    public ConfInputException() {
    }

    public ConfInputException(String message) {
        super(message);
    }

    public ConfInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfInputException(Throwable cause) {
        super(cause);
    }

    public ConfInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
