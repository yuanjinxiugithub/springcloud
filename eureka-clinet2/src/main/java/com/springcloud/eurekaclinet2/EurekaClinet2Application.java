package com.springcloud.eurekaclinet2;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class EurekaClinet2Application {

    public static void main(String[] args) {
        SpringApplication newRun= new SpringApplication(EurekaClinet2Application.class);
        newRun.setBannerMode(Banner.Mode.OFF);
        newRun.run(args);
    }

}

