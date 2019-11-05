package com.blogserver.userclient.service;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;

/**
 * 用户查询的service接口
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 11:26
 */
public interface UserQueryService {

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    ResultSet<UserVO> selectByUserName(String username);

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    ResultSet<UserVO> selectById(Long id);

}
