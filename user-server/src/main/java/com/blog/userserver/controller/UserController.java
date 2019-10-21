package com.blog.userserver.controller;

import com.blog.userserver.mapper.UserMapper;
import com.blog.userserver.server.UserQueryServer;
import com.zlz.common.entity.user.UserVO;
import com.zlz.common.result.ResultSet;
import com.zlz.common.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息操作controller层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 11:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserQueryServer userServer;

    @PostMapping("/userlist")
    public ResultSet getUserList(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getUserList(pageInfo);
    }

    @PostMapping("/adminlist")
    public ResultSet getAdminList(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getAdminList(pageInfo);
    }

    @PostMapping("/userinfo")
    public ResultSet getUserInfo(@RequestBody String username){
        return userServer.getUserByName(username);
    }

    @PostMapping("/admininfo")
    public ResultSet getDdminInfo(@RequestBody String username){
        return userServer.getAdminByName(username);
    }
}
