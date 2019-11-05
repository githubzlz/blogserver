package com.blogserver.userclient.service;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户修改相关接口
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/1 18:00
 */
public interface UserUpdateService {

    /**
     * 通过id修改用户信息
     * @param user user
     * @param request request
     * @return 执行结果
     */
    ResultSet updateUserInfo(UserVO user, HttpServletRequest request);

    /**
     * 修改用户密码
     * @param user user
     * @return 执行结果
     */
    ResultSet updatePassword(UserVO user);

    /**
     * 设置管理员（管理员权限）
     * @param user user
     * @param request request
     * @return
     */
    ResultSet setAdmin(UserVO user, HttpServletRequest request);

    /**
     * 封禁用户（管理员权限）
     * @param id id
     * @param request request
     * @return
     */
    ResultSet banUser(Long id, HttpServletRequest request);
}
