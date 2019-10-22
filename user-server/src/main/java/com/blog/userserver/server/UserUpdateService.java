package com.blog.userserver.server;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;

/**
 * 修改用户信息
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 8:34
 */
public interface UserUpdateService {

    /**
     * 通过用户id修改用户信息
     * @param userVO userVO
     * @return ResultSet
     */
    ResultSet updateUserInfoById(UserVO userVO);

}
