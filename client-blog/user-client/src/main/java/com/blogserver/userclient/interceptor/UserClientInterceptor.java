package com.blogserver.userclient.interceptor;

import com.blog.common.constants.BaseConstants;
import com.blog.common.constants.ClientConstants;
import com.blog.common.result.ClientInfo;
import com.blog.common.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/24 16:38
 */
@Component
public class UserClientInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //设置客户端信息
        ClientInfo client = TokenUtil.getClient("user", "secret",
                ClientConstants.REQUEST_USER, ClientConstants.TOKEN_USER);

        //获取token
        String token = TokenUtil.getToken(request, ClientConstants.TOKEN_USER);
        if(token == null){
            System.out.println("------------------拦截器开始拦截----------------------");

            //获取请求的路径,用于之后的重定向
            HttpSession session = request.getSession();
            String url = "http://" + request.getHeader("Host")+request.getServletPath();
            session.removeAttribute(ClientConstants.REQUEST_USER);
            System.out.println("拦截器设置重定向路径----"+ ClientConstants.REQUEST_USER+" : "+url);
            session.setAttribute(ClientConstants.REQUEST_USER, url);

            //重定向到 token中心 去授权中心获取token
            System.out.println("向token处理中心发起请求：/token/setToken");

            session.setAttribute(ClientConstants.USER, client);
            response.sendRedirect("http://"+ BaseConstants.MYWEB_HOST +":9000/token/setToken/"+ClientConstants.USER);
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
