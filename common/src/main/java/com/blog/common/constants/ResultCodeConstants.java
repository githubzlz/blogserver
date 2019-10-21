package com.blog.common.constants;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 10:03
 */
public class ResultCodeConstants {

    /**
     * 成功
     */
    public static final Integer SUCCESS = 1;
    public static final String SUCCESS_MSG = "操作成功";
    /**
     * 失败，系统异常
     */
    public static final Integer SYS_ERROR = -1;
    public static final String SYS_ERROR_MSG = "操作失败，系统错误";
    /**
     * 失败，输入异常
     */
    public static final Integer INPUT_ERROR = -2;
    public static final String INPUT_ERROR_MSG = "操作失败，请输入正确的值";
    /**
     * 失败，返回异常
     */
    public static final Integer OUT_ERROR = -3;
    public static final String OUT_ERROR_MSG = "操作失败，请重新尝试";
}
