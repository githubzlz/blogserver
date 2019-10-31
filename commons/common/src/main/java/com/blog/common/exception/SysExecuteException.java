package com.blog.common.exception;

/**
 * 系统执行错误
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 10:57
 */
public class SysExecuteException extends RuntimeException{
    public SysExecuteException() {
    }

    public SysExecuteException(String message) {
        super(message);
    }

    public SysExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysExecuteException(Throwable cause) {
        super(cause);
    }

    public SysExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
