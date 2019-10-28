package com.blogserver.userclient.token;

import com.blog.common.constants.BaseConstants;
import com.blog.common.constants.TokenConstants;
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
        System.out.println("即将去获取code--------");
        response.sendRedirect("http://"+BaseConstants.MYWEB_HOST+":8080/oauth/authorize?client_id=user&response_type=code");
    }

    /**
     * 获取到token
     * @param code
     * @return
     * @throws IOException
     */
    @RequestMapping("/code")
    public String code(@RequestParam("code") String code,
                       HttpServletRequest request,HttpServletResponse response) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        StringBuffer params = new StringBuffer();
        params.append("?grant_type=authorization_code").append("&");
        params.append("code=").append(code);
        HttpPost post = new HttpPost("http://user:secret@"+ BaseConstants.MYWEB_HOST +":8080/oauth/token"+params);

        System.out.println("获取code成功，code："+code);

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
        String requestUrl = TokenUtil.setToken(request, TokenConstants.TOKEN_USER,
                token, TokenConstants.REQUEST_USER);

        if(requestUrl != null){
            response.sendRedirect(requestUrl);
        }
        return "未重定向成功";
    }
}
