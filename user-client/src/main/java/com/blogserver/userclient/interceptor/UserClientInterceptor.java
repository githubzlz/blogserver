package com.blogserver.userclient.interceptor;

import com.blog.common.constants.TokenConstants;
import com.blog.common.util.TokenUtil;
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
public class UserClientInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return TokenUtil.handlerSession(request, response, TokenConstants.TOKEN_USER, TokenConstants.REQUEST_USER);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
