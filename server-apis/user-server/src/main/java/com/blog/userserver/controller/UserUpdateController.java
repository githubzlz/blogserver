package com.blog.userserver.controller;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blog.userserver.server.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 修改用户信息操作controller层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 9:40
 */
@RestController
@RequestMapping("/update")
public class UserUpdateController {

    @Autowired
    private UserUpdateService userServer;

    @PostMapping("/user_id")
    public ResultSet updateUserInfo(@RequestBody UserVO userVO){
        return userServer.updateUserInfoById(userVO);
    }
}
