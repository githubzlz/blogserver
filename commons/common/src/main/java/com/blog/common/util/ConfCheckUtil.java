package com.blog.common.util;

import com.blog.common.exception.ConfInputException;
import com.blog.common.exception.ConfOutputException;
import com.blog.common.exception.SysExecuteException;

/**
 * 参数检查的工具
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 10:07
 */
public class ConfCheckUtil {

    /**
     * 对参数进行非空验证
     * @param value
     */
    public static void inputNotNullCheck(Object... value) throws ConfInputException{
        for(int i=0; i<value.length; i++ ){
            if(value[i] == null){
                throw new ConfInputException("输入参数错误");
            }
        }
    }

    /**
     * 对输出的参数进行非空验证
     * @param value
     */
    public static void outputNotNullCheck(Object... value) throws ConfOutputException{
        for(int i=0; i<value.length; i++ ){
         if(value[i] == null){
                throw new ConfOutputException("没有查询到数据");
            }
        }
    }

    /**
     * 对一行数据的操作进行判断，若是操作影响的行数不为1，则抛出系统执行异常
     * @param row
     */
    public  static void oneRowCheck(int row){
        if(row != 1){
            throw new SysExecuteException("系统执行异常，请联系管理员");
        }
    }

}
