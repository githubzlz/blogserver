package com.blog.userserver.server;

import com.blog.common.entity.user.UserVO;
import com.blog.common.exception.CreateConfException;
import com.blog.common.exception.SysExecuteException;
import com.blog.common.result.ResultSet;

/**
 * 创建用户
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 10:08
 */
public interface UserCreateService {

    /**
     * 创建新用户
     * @param userVO
     * @return
     * @throws CreateConfException
     * @throws SysExecuteException
     */
    ResultSet createUser(UserVO userVO) throws CreateConfException, SysExecuteException;

}
