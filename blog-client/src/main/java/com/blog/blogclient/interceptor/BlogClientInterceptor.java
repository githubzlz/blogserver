package com.blog.blogclient.interceptor;

import com.blog.common.entity.token.Token;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/24 16:38
 */
@Component
public class BlogClientInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------------------拦截器:"+ request.getServletPath()+"----------------------");
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for(Cookie cookie : cookies){
                System.err.print(cookie.getName()+" : "+cookie.getValue());
            }
        }
        HttpSession session = request.getSession();
        session.removeAttribute("request-url");
        session.setAttribute("request-url", request.getServletPath());

        Token token = TokenUtil.getToken(request);
        if(token == null){
            response.sendRedirect("/token/user_login");
            return false;
        }

        String tokenValue = token.getToken();
        if(tokenValue == null){
            response.sendRedirect("/token/user_login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}