package com.blog.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 有关登录人的工具类
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/25 15:21
 */
public class LoginUserUtil {

    /**
     * 获取登陆人用户名
     * @param token
     * @return
     */
    public static String getLoginUser(String token){
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim("user_name").asString();
    }

    /**
     * 获取登陆人用户名
     * @param request
     * @return
     */
    public static String getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("login_user");
        if(user == null){
            return "未登录";
        }
        return (String)user;
    }
}
