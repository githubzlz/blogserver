package com.blogserver.userclient.token;

import com.blog.common.entity.token.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * token的工具类
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 17:24
 */
public class TokenUtil {

    /**
     * 获取token
     * @return token
     */
    public static Token getToken(HttpServletRequest request){
        HttpSession session = request.getSession();
        Token token = null;
        if(session.getAttribute("login_token") != null){
            token = (Token) session.getAttribute("login_token");
        }
        return token;
    }
}
