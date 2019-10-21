package com.blog.userserver.controller;

import com.blog.userserver.server.UserQueryServer;
import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blog.common.util.PageInfo;
import com.blog.userserver.util.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息操作controller层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 11:41
 */
@RestController
public class UserController {

    @Autowired
    private UserQueryServer userServer;

    @GetMapping("/getLoginUser")
    public String test(HttpServletRequest request){
        return LoginUser.getLoginUser(request);
    }

    @PostMapping("/userlist")
    public ResultSet getUserList(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getUserList(pageInfo);
    }

    @PostMapping("/adminlist")
    public ResultSet getAdminList(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getAdminList(pageInfo);
    }

    @PostMapping("/userinfo")
    public ResultSet getUserInfo(@RequestBody UserVO userVO){
        return userServer.getUserByName(userVO.getUsername());
    }

    @PostMapping("/admininfo")
    public ResultSet getAdminInfo(@RequestBody UserVO userVO){
        return userServer.getAdminByName(userVO.getUsername());
    }
}
