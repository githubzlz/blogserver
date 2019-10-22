package com.blog.userserver.server;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建用户
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 10:08
 */
public interface UserCreateService {

    /**
     * 创建新用户
     * @param userVO
     * @param request
     * @return
     */
    ResultSet createUser(UserVO userVO, HttpServletRequest request);

}
