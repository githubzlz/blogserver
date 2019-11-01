package com.blog.apisgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/31 16:50
 */
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String uri = requestContext.getRequest().getRequestURI();
        HttpSession session = requestContext.getRequest().getSession();

        //获取session检查登陆状态,若是未登录则重定向去sso登录登陆
        Object ob = session.getAttribute("is_login");
        if(ob == null || !"true".equals((String)ob)){
            sendRedirectToLogin(requestContext, uri);
        }else {
            //若是已经登陆则从session中获取token，并添加到请求头中
            Object access_token = session.getAttribute("blog_client");
            if(access_token != null){
                String token = null;
                try {
                    token = URLEncoder.encode((String)access_token, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                requestContext.addZuulRequestHeader("authorization", "Bearer "+ token);
            }else {
                //若是没有token为获取到，重定向去登录
                sendRedirectToLogin(requestContext, uri);
            }
        }
        return null;
    }

    /**
     * 重定向到登陆界面，并停止代理转发
     * @param requestContext
     */
    private void sendRedirectToLogin(RequestContext requestContext, String uri){
        try {
            requestContext.getResponse().sendRedirect("http://www.blogzlz.com:9000/sso/login?redirect_url=" + uri);
            requestContext.setSendZuulResponse(false);
        } catch (IOException e) {
            System.out.println("重定向异常");
            e.printStackTrace();
        }
    }
}
