package com.blog.adminserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/30 18:06
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
