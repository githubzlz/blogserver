package com.blogserver.userclient.controller;

import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blogserver.userclient.service.UserCreateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户创建的控制器层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 10:44
 */
@RestController
@RequestMapping("/new")
public class UserCreateController {

    @Resource
    private UserCreateService userCreateService;

    /**
     * 创建新用户
     * @param userVO userVO
     * @return 执行结果
     */
    @PostMapping("/user")
    public ResultSet createUser(@RequestBody UserVO userVO){
        return userCreateService.createNew(userVO);
    }
}
