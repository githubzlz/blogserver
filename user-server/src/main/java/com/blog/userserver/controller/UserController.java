package com.blog.userserver.controller;

import com.blog.userserver.server.UserServer;
import com.zlz.common.entity.UserVO;
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
@RequestMapping("/info")
public class UserController {

    @Autowired
    private UserServer userServer;

    @PostMapping("/user/{username}")
    public ResultSet getUserInfo(@RequestBody PageInfo<UserVO> pageInfo, @PathVariable("username")String username){
        return userServer.getUser(pageInfo, username);
    }

    @PostMapping("/admin/{username}")
    public ResultSet getAdminInfo(@RequestBody PageInfo<UserVO> pageInfo, @PathVariable("username")String username){
        return userServer.getAdmin(pageInfo, username);
    }
}
