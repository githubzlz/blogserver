package com.blog.userserver.server;

import com.blog.common.entity.user.UserVO;
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
    ResultSet getUserList(PageInfo<UserVO> pageInfo);

    /**
     * 获取管理员信息列表
     * @param pageInfo pageInfo
     * @return list
     */
    ResultSet getAdminList(PageInfo<UserVO> pageInfo);

    /**
     * 通过用户id获取用户信息
     * @param userVO id
     * @return userVO
     */
    ResultSet getUserById(UserVO userVO);

    /**
     * 通过用户id获取用户信息
     * @param userVO id
     * @return userVO
     */
    ResultSet getAdminById(UserVO userVO);

    /**
     * 通过用户名获取用户信息
     * @param userVO username
     * @return list
     */
    ResultSet getUserByUserName(UserVO userVO);

    /**
     * 通过用户名获取用户信息
     * @param userVO username
     * @return list
     */
    ResultSet getAdminByUserName(UserVO userVO);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    UserVO getUserInfoByName(String username);
}
