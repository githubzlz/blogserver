package com.blog.userserver.server;

import com.zlz.common.entity.user.UserVO;
import com.zlz.common.result.ResultSet;
import com.zlz.common.util.PageInfo;

/**
 * 用户信息
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/18 17:53
 */
public interface UserQueryServer {

    /**
     * 获取用户信息列表
     * @param pageInfo pageInfo
     * @return 执行结果
     */
    ResultSet getUserList(PageInfo<UserVO> pageInfo);

    /**
     * 获取管理员信息列表
     * @param pageInfo pageInfo
     * @return 执行结果
     */
    ResultSet getAdminList(PageInfo<UserVO> pageInfo);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    ResultSet getUserByName(String username);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    ResultSet getAdminByName(String username);
}
