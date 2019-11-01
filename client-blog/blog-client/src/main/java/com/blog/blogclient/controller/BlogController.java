package com.blog.blogclient.controller;

import com.blog.blogclient.feign.BlogFeignService;
import com.blog.common.constants.ClientConstants;
import com.blog.common.result.PageInfo;
import com.blog.common.util.TokenUtil;
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
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogFeignService userFeignService;

    @RequestMapping("/client")
    public String test(){
        return "BLOG-CLIENT";
    }

    @GetMapping("/test")
    public String getUserList(HttpServletRequest request, HttpServletResponse response){
        return userFeignService.getTest();
    }
}
