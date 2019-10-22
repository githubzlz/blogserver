package com.blog.userserver.controller;

import com.blog.userserver.server.UserQueryService;
import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blog.common.result.PageInfo;
import com.blog.userserver.util.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 查询用户信息操作controller层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 11:41
 */
@RestController
@RequestMapping("/query")
public class UserQueryController {

    @Autowired
    private UserQueryService userServer;

    @GetMapping("/get_login_user")
    public String test(HttpServletRequest request){
        return LoginUser.getLoginUser(request);
    }

    @PostMapping("/user_page")
    public ResultSet getUserList(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getUserList(pageInfo);
    }

    @PostMapping("/admin_page")
    public ResultSet getAdminList(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getAdminList(pageInfo);
    }

    @PostMapping("/user_info_id")
    public ResultSet getUserInfo(@RequestBody UserVO userVO){
        return userServer.getUserById(userVO);
    }

    @PostMapping("/admin_info_id")
    public ResultSet getAdminInfo(@RequestBody UserVO userVO){
        return userServer.getAdminById(userVO);
    }

    @PostMapping("/user_list_username")
    public ResultSet getUserList(@RequestBody UserVO userVO){
        return userServer.getUserByUserName(userVO);
    }

    @PostMapping("/admin_list_username")
    public ResultSet getAdminList(@RequestBody UserVO userVO){
        return userServer.getAdminByUserName(userVO);
    }
}
