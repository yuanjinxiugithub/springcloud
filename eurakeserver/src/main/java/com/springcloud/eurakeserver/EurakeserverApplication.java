package com.springcloud.eurakeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  使用 @EnableEurekaServer注解 标明服务注册中心启动类
 *  该类用于启动服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class EurakeserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurakeserverApplication.class, args);
    }

}

