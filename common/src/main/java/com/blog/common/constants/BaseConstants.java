package com.blog.common.constants;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/28 16:25
 */
public class BaseConstants {

    /**
     * 我的网址，由于项目没采用跨域session共享，一切session操作都是基于
     * cookie中JSESSIONID，获取session储存的信息，如果域名改变cookie不会被携带
     * session共享会失效
     */
    public static final String MYWEB_HOST = "www.blogzlz.com";
}
