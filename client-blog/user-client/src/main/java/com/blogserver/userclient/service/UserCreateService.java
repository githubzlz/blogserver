package com.blogserver.userclient.service;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;

/**
 * 创建用户的service层接口
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 9:50
 */
public interface UserCreateService{

    /**
     * 创建新用户
     * @param userVO 用户实体类
     * @return 执行结果
     */
    ResultSet<UserVO> createNew(UserVO userVO);
}
