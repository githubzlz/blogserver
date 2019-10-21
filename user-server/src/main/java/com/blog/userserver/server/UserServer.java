package com.blog.userserver.server;

import com.zlz.common.entity.UserVO;
import com.zlz.common.result.ResultSet;
import com.zlz.common.util.PageInfo;

/**
 * 用户信息
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/18 17:53
 */
public interface UserServer {

    /**
     * 获取用户信息
     * @param username 用户名
     * @param pageInfo pageInfo
     * @return 执行结果
     */
    ResultSet getUser(PageInfo<UserVO> pageInfo, String username);

    /**
     * 获取管理员信息
     * @param username 用户名
     * @param pageInfo pageInfo
     * @return 执行结果
     */
    ResultSet getAdmin(PageInfo<UserVO> pageInfo, String username);

}
