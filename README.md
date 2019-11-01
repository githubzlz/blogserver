# blog-server
        该微服务体系为博客论坛网站，架构以高可用为主，使用了configration配置中心，eureka集群，zuul网关，oauth2.0服务保护，
     oauth2.0授权中心，redis+spring session分布式session共享，应用单点登录（暂不支持跨域）等技术，可以以JWT令牌验证方式对
     第三方应用提供查询api。
    
###1. spring cloud微服务技术栈:

    1).spring cloud netflix eureka 注册中心
    2).spring cloud netflix zuul 统一网关
    3).spring cloud netflix ribbon 负载均衡
    4).spring cloud netflix feign 声明式Rest客户端
    5).spring cloud netflix hystrix 熔断器（现在未使用）
    6).spring cloud config 配置中心
    7).spring session + redis session共享
    8).mysql 数据库 
    9).
###2. spring cloud微服务模块
    
####1. commons
    
    1.common模块
        提供通用方法，各种实体类，util方法。个模块导入之后使用。
    2.config-server微服务
        配置中心，为各个微服务提供统一的配置。
    3.eureka-server(集群)
        注册中心，各个微服务在注册中心注册，注册中心保存各个微服务的信息。
    4.oauth-server
        授权中心，微服务的授权中心，负责token的分发与验证。
   
####2. client-blog
     
    1.client-gateway(集群)
        客户端统一网关，所有的客户端微服务不对外暴露，请求经过网关路由之后访问客户端，此处利用ribbon实现了负载均衡。
    2.sso-center(可有可无)
        统一处理客户端的登录业务，做为登陆中心，负责与oauth2授权中心进行交互，获取token并将token储存到session中。          
    3.admin-client,blog-client,user-client(客户端)
        处理请求，提供服务，负责与用户的交互。使用security进行保护，访问需要token。
     
####3. server-apis
    
    1. apis-gateway(集群)
        api统一网关，对api微服务统一保护，第三方请求api时经过改微服务的路由之后，才能调用server微服务。该微服务使用
    security oauth2进行保护，第三方调用api需要注册客户端，向oauth2申请token之后进行访问。
    2. blog-server，user-server(api)
        对外提供可以查询相关数据的api，需经过apis-gateway路由。对内提供api，可不经过路由直接访问。api微服务不对外直接暴露。