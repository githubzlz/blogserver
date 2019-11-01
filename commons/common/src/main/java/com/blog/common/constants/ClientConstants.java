package com.blog.common.constants;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/28 14:28
 */
public class ClientConstants {

    /**
     * 客户端名称
     */
    public static final String BLOG = "blog_client";

    /**
     * session中token的key
     * 三种token分别属于user，admin，blog的服务
     */
    public static final String TOKEN_API = "token_apis_gateway";
    public static final String TOKEN_BLOG = "token_blog";

    /**
     * 重定向时session中储存的名字
     */
    public static final String REQUEST_BLOG = "request_blog";

}
