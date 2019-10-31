package com.blog.userserver.controller;

import com.blog.common.constants.UserConstants;
import com.blog.common.entity.user.UserVO;
import com.blog.common.exception.CreateConfException;
import com.blog.common.exception.SysExecuteException;
import com.blog.common.result.ResultSet;
import com.blog.userserver.server.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 10:35
 */
@RestController
@RequestMapping("/create")
public class UserCreateController {

    @Autowired
    private UserCreateService userService;

    @PostMapping("/user")
    public ResultSet createUser(@RequestBody UserVO userVO)
            throws CreateConfException, SysExecuteException {
        userVO.setIsAdmin(UserConstants.IS_NOT_ADMIN);
        return userService.createUser(userVO);
    }

    @PostMapping("/admin")
    public ResultSet createAdmin(@RequestBody UserVO userVO)
            throws CreateConfException, SysExecuteException{
        userVO.setIsAdmin(UserConstants.IS_ADMIN);
        return userService.createUser(userVO);
    }

}
