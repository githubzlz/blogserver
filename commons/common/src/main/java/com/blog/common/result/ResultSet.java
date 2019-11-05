package com.blog.common.result;

import com.blog.common.constants.ResultCodeConstants;

import java.io.Serializable;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 9:59
 */
public class ResultSet<T> implements Serializable{

    /**
     * 返回的消息
     */
    private String message;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回的实体
     */
    private T entity;

    private ResultSet(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    private ResultSet(String message, Integer code, T entity) {
        this.message = message;
        this.code = code;
        this.entity = entity;
    }

    /**
     * 返回成功的消息和实体
     * @param entity
     * @return
     */
    public static ResultSet success(Object entity){
        return new ResultSet<>(ResultCodeConstants.SUCCESS_MSG, ResultCodeConstants.SUCCESS,entity);
    }

    /**
     * 返回成功的消息
     * @return
     */
    public static ResultSet success(){
        return new ResultSet(ResultCodeConstants.SUCCESS_MSG, ResultCodeConstants.SUCCESS);
    }

    /**
     * 由于系统失败时返回的消息
     * @return
     */
    public static ResultSet sysError(){
        return new ResultSet(ResultCodeConstants.SYS_ERROR_MSG, ResultCodeConstants.SYS_ERROR);
    }

    /**
     * 由于输入参数问题返回的消息
     * @return
     */
    public static ResultSet inputError(){
        return new ResultSet(ResultCodeConstants.INPUT_ERROR_MSG, ResultCodeConstants.INPUT_ERROR);
    }

    /**
     * 由于查询结果问题返回的消息
     * @return
     */
    public static ResultSet outError(){
        return new ResultSet(ResultCodeConstants.OUT_ERROR_MSG, ResultCodeConstants.OUT_ERROR);
    }

    public static ResultSet executeError(){
        return new ResultSet(ResultCodeConstants.OUT_ERROR_MSG, ResultCodeConstants.OUT_ERROR);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
