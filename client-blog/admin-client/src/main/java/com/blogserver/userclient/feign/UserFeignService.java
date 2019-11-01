package com.blogserver.userclient.feign;

import com.blog.common.result.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 17:13
 */
@FeignClient(name = "user-server")
public interface UserFeignService {

    /**
     * 请求token
     * @param pageInfo
     * @return code
     */
    @GetMapping("/query/all_user_page")
    String getUserList(@RequestBody PageInfo pageInfo);

}
