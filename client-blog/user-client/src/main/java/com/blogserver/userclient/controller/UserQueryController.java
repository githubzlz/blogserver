package com.blogserver.userclient.controller;

import com.blog.common.result.PageInfo;
import com.blogserver.userclient.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/24 11:15
 */

@RestController
@RequestMapping("/query")
public class UserQueryController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/getUserList")
    public String getUserList(HttpServletRequest request, HttpServletResponse response){
        return null;
    }
}
