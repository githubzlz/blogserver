package com.blogserver.userclient.controller;

import com.blog.common.constants.TokenConstants;
import com.blog.common.result.PageInfo;
import com.blog.common.util.TokenUtil;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/getUserList")
    public String getUserList(HttpServletRequest request, HttpServletResponse response){
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(10L);
        pageInfo.setPageNum(1L);
        String token = TokenUtil.getToken(request, TokenConstants.TOKEN_USER);

        System.out.println("正在使用token访问api："+token);
        return userFeignService.getUserList(pageInfo, token);
    }
}
