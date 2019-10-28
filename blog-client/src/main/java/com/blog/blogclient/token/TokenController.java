package com.blog.blogclient.token;

import com.blog.common.entity.token.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/23 17:22
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    /**
     * 获取token的请求
     * @param response
     * @throws IOException
     */
    @GetMapping("/user_login")
    public void getCode(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/oauth/authorize?client_id=blog&response_type=code");
    }

    /**
     * 获取到token
     * @param code
     * @return
     * @throws IOException
     */
    @RequestMapping("/code")
    public String getToken(@RequestParam("code") String code,
                       HttpServletRequest request,HttpServletResponse response) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        StringBuffer params = new StringBuffer();
        params.append("?grant_type=authorization_code").append("&");
        params.append("code=").append(code);
        HttpPost post = new HttpPost("http://blog:secret@localhost:8080/oauth/token"+params);

        CloseableHttpResponse cResponse = null;
        cResponse = httpClient.execute(post);
        HttpEntity responseEntity = cResponse.getEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(EntityUtils.toString(responseEntity), Map.class);
        String token = map.get("access_token").toString();
        String overTime = map.get("expires_in").toString();
        Token tokenEntity = new Token();
        tokenEntity.setToken("Bearer " +token);
        tokenEntity.setOverTime(overTime);

        //将token储存到session中保存用户的登陆状态
        HttpSession session = request.getSession();
        session.setAttribute("login_token", tokenEntity);
        String requestUrl = (String) session.getAttribute("request-url");

        if(requestUrl != null){
            response.sendRedirect(requestUrl);
        }
        return "登录成功";
    }
}
