package com.blog.ssocenter.controller;

import com.blog.common.constants.BaseConstants;
import com.blog.common.constants.ClientConstants;
import com.blog.common.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/1 9:29
 */
@RestController
@RequestMapping("/sso")
public class LoginHandleController {

    private static String url;

    @GetMapping("/login/check")
    public boolean booleanLogin(HttpServletRequest request){
        Object ob = request.getSession().getAttribute("is_login");
        if(ob != null){
            return (Boolean) ob;
        }
        return false;
    }

    @GetMapping("/login")
    public void login(@RequestParam("redirect_url")String url, HttpServletResponse response) throws IOException {
        LoginHandleController.url = url;
        response.sendRedirect("http://www.blogzlz.com:8080/oauth/authorize?client_id=blog_client&response_type=code");
    }

    @GetMapping("/setToken")
    public void getToken(@RequestParam(value = "code",required = false) String code,
                         HttpServletRequest request, HttpServletResponse response) throws IOException {

        //创建发送http请求的客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //设置post请求的参数
        StringBuffer params = new StringBuffer();
        params.append("?grant_type=authorization_code").append("&");
        params.append("code=").append(code);
        System.out.println("code"+code);
        //设计post请求，发送请求
        HttpPost post = new HttpPost("http://blog_client:secret@"
                +BaseConstants.MYWEB_HOST +":8080/oauth/token"+params);
        CloseableHttpResponse cResponse = null;
        cResponse = httpClient.execute(post);

        //获取返回的结果（json数据），并解析
        HttpEntity responseEntity = cResponse.getEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(EntityUtils.toString(responseEntity), Map.class);
        String token = map.get("access_token").toString();

        //将token设置进session（域为：www.blogzlz.com）
        if(token != null){
            HttpSession session = request.getSession();
            //将token保存到session
            session.setMaxInactiveInterval(3600);
            session.setAttribute(ClientConstants.BLOG, URLEncoder.encode(token, "utf-8"));
            session.setAttribute("is_login", "true");
        }
        if(url != null){
            response.sendRedirect("http://www.blogzlz.com:20000" + url);
            url = null;
        }else {
            response.sendRedirect("http://www.blogzlz.com:20020/index.html");
        }
    }
}
