package com.blog.apisgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ApisGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApisGatewayApplication.class, args);
    }

}
