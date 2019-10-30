package com.blog.tokencenter.interceptor;

import com.blog.common.util.WebUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/24 16:38
 */
@Component
public class ClientInterceptor implements HandlerInterceptor{

    @Value("${client.access.host}")
    private String clientHost;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String ip = WebUtil.getRealIp(request);

        System.out.print("token中心拦截的地址为："+ip);

        if(clientHost.equals(ip)){
            System.out.println("----访问通过");
            return true;
        }
        System.out.println("----禁止访问");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
