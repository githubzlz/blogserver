package com.blog.common.util;

import com.blog.common.exception.ConfInputException;
import com.blog.common.exception.ConfOutputException;

/**
 * 公共的工具
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 10:07
 */
public class CommonUtil {

    /**
     * 对参数进行非空验证
     * @param value
     */
    public static void inputNotNullCheck(Object... value) throws ConfInputException{
        for(int i=0; i<value.length; i++ ){
            if(value == null){
                throw new ConfInputException("输入参数错误");
            }
        }
    }

    public static void outputNotNullCheck(Object... value) throws ConfOutputException{
        for(int i=0; i<value.length; i++ ){
         if(value == null){
                throw new ConfOutputException("没有查询到数据");
            }
        }
    }
}
