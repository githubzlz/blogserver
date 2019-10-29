package com.blog.common.util;

import com.blog.common.result.ClientInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/28 15:10
 */
public class TokenUtil {

    /**
     * session中设置token
     * @param request
     * @param tokenName
     * @param token
     * @return
     */
    public static String setToken(HttpServletRequest request, String tokenName, String token, String requestUrl){

        //设置用户名,设置前先移除
        HttpSession session = request.getSession();
        String username = LoginUserUtil.getLoginUser(token);
        session.removeAttribute("username");
        session.setAttribute("username", username);

        //将token设置到session
        session.setAttribute(tokenName, "Bearer " +token);

        System.out.println("已获取到token---"+tokenName+" : "+token);
        System.out.println("已获取到用户名："+username);

        //获取重定向路径并返回
        Object url = session.getAttribute(requestUrl);
        if(url != null){
            System.out.println("即将重定向到该地址："+(String) url);
            return (String) url;
        }
        return null;
    }

    /**
     * 从session中获取token
     * @param request
     * @param tokenName
     * @return
     */
    public static String getToken(HttpServletRequest request, String tokenName){
        HttpSession session = request.getSession();
        String token = null;
        if(session.getAttribute(tokenName) != null){
            token = (String) session.getAttribute(tokenName);
        }
        return token;
    }

    /**
     * 拦截器对session的处理
     * @param request
     * @param response
     * @param tokenName
     * @return
     * @throws IOException
     */
    public static void handlerSession(HttpServletRequest request, HttpServletResponse response,
                                         String tokenName, String requestUrl) throws IOException {

        String token = getToken(request, tokenName);
        if(token == null){
            System.out.println("------------------拦截器开始拦截----------------------");

            //获取请求的路径,用于之后的重定向
            HttpSession session = request.getSession();
            session.removeAttribute(requestUrl);
            System.out.println("拦截器设置重定向路径----"+requestUrl+" : "+request.getServletPath());
            session.setAttribute(requestUrl, request.getServletPath());

            //重定向到 登录请求 去授权中心获取token
            System.out.println("向token处理中心发起请求：/token/setToken");
        }
    }

    /**
     * 创建客户端对象
     * @param clientId
     * @param secret
     * @param requestUrl
     * @param TokenName
     * @return
     */
    public static ClientInfo getClient(String clientId, String secret, String requestUrl, String TokenName){
        ClientInfo client = new ClientInfo();
        client.setClientId(clientId);
        client.setSecret(secret);
        client.setRequestUrl(requestUrl);
        client.setTokenName(TokenName);
        return client;
    }

}
