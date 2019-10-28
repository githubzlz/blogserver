package com.blogserver.userclient.config;

import com.blogserver.userclient.interceptor.UserClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/24 16:54
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private UserClientInterceptor interceptor;
    /**
     * 实现拦截器 要拦截的路径以及不拦截的路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns("/token/**");
    }
}
