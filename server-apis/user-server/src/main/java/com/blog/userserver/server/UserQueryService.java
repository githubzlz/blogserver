package com.blog.userserver.server;

import com.blog.common.entity.user.UserVO;
import com.blog.common.exception.ConfInputException;
import com.blog.common.exception.ConfOutputException;
import com.blog.common.result.ResultSet;
import com.blog.common.result.PageInfo;

/**
 * 查询用户信息
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/18 17:53
 */
public interface UserQueryService {

    /**
     * 获取用户信息列表
     * @param pageInfo pageInfo
     * @return list
     */
    ResultSet getUserPage(PageInfo<UserVO> pageInfo,Integer isAdmin)
            throws ConfInputException, ConfOutputException;

    /**
     * 通过用户id获取用户信息
     * @param userVO id
     * @return userVO
     */
    ResultSet getUserById(UserVO userVO,Integer isAdmin)
            throws ConfInputException, ConfOutputException;

    /**
     * 通过用户名获取用户信息
     * @param userVO username
     * @return list
     */
    ResultSet getUserByUserName(UserVO userVO,Integer isAdmin)
            throws ConfInputException, ConfOutputException;

    /**
     * 通过用户名获取用户信息
     * @param column 字段
     * @param value 值
     * @param column2 字段
     * @param value2 值
     * @return UserVO
     */
    UserVO getUserInfo(String column,Object value, String column2, Object value2);
}
