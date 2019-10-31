package com.blog.tokencenter.controller;

import com.blog.common.constants.BaseConstants;
import com.blog.common.result.ClientInfo;
import com.blog.common.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/28 17:01
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Bean
    private ClientInfo client(){
        return new ClientInfo();
    }

    @Autowired
    private ClientInfo clientInfo;

    @GetMapping("/setToken/{clientId}")
    public String codeGet(@PathVariable("clientId") String clientId, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        System.out.println("进入token处理中心，即将去获取code--------");
        HttpSession session = request.getSession();

        //客户端信息用完删除
        Object client = session.getAttribute(clientId);
        session.removeAttribute(clientId);

        System.out.println("--------客户端信息为："+clientId+" : "+client);

        if(client == null){
            return "客户端信息为空";
        }

        clientInfo = (ClientInfo) client;

        if(clientInfo.getClientId() != null){
            String scope = URLEncoder.encode("博客论坛", "utf8");
            response.sendRedirect("http://"+ BaseConstants.MYWEB_HOST+":8080/oauth/authorize?client_id=" +
                    clientInfo.getClientId() + "&response_type=code"+"&scope="+scope);
        }
        return "错误的客户端信息";
    }

    @GetMapping("/token")
    public String code(@RequestParam("code") String code,
                       HttpServletRequest request, HttpServletResponse response) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        System.out.println("获取code成功，code："+code);

        StringBuffer params = new StringBuffer();
        params.append("?grant_type=authorization_code").append("&");
        params.append("code=").append(code);

        HttpPost post = new HttpPost("http://"+ clientInfo.getClientId() +
                ":"+clientInfo.getSecret()+"@"+ BaseConstants.MYWEB_HOST +":8080/oauth/token"+params);

        CloseableHttpResponse cResponse = null;

        System.out.println("正在请求token--------");

        cResponse = httpClient.execute(post);
        HttpEntity responseEntity = cResponse.getEntity();
        ObjectMapper objectMapper = new ObjectMapper();

        Map map = objectMapper.readValue(EntityUtils.toString(responseEntity), Map.class);
        String token = map.get("access_token").toString();
        String overTime = map.get("expires_in").toString();

        System.out.println("请求获得token："+token);

        //设置session
        String requestUrl = TokenUtil.setToken(request, clientInfo.getTokenName(),
                token, clientInfo.getRequestUrl());
        if(requestUrl != null){
            response.sendRedirect(requestUrl);
        }
        return "未重定向成功";
    }
    
}
