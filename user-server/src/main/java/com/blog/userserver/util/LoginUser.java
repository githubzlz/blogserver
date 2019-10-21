package com.blog.userserver.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 17:50
 */
public class LoginUser {
    public static String getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = securityContext.getAuthentication();
        return authentication.getPrincipal().toString();
    }
}
