package com.blog.blogclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 17:13
 */
@FeignClient(name = "blog-server")
public interface BlogFeignService {

    /**
     * 请求token
     * @return code
     */
    @GetMapping("/test")
    String getTest();

}
