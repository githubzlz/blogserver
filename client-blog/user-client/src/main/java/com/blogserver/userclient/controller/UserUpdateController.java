package com.blogserver.userclient.controller;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blogserver.userclient.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息修改的控制器层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 9:47
 */
@RestController
@RequestMapping("/change")
public class UserUpdateController {

    @Resource
    private UserUpdateService userUpdateService;

    /**
     * 修改密码
     * @param userVO
     * @return
     */
    @PostMapping("/password")
    public ResultSet changePwd(@RequestBody UserVO userVO){
        return userUpdateService.updatePassword(userVO);
    }

    /**
     * 修改用户信息（不敏感）
     * @param userVO
     * @param request
     * @return
     */
    @PostMapping("/info")
    public ResultSet changeInfo(@RequestBody UserVO userVO,HttpServletRequest request){
        return userUpdateService.updateUserInfo(userVO, request);
    }
}
