package com.blog.common.exception;

/**
 * 系统权限相关异常
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 17:09
 */
public class AuthorityException extends RuntimeException {
    public AuthorityException() {
    }

    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityException(Throwable cause) {
        super(cause);
    }

    public AuthorityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
