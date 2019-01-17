package com.springcloud.serverauth;

import com.springcloud.serverauth.config.FilterIgnorePropertiesConfig;
import com.springcloud.serverauth.properties.SecurityConstants;
import com.springcloud.serverauth.properties.SecurityProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FilterIgnorePropertiesConfig.class, SecurityProperties.class})
public class ServerAuthApplication {

    public static void main(String[] args) {
        SpringApplication newRun= new SpringApplication(ServerAuthApplication.class);
        newRun.setBannerMode(Banner.Mode.OFF);
        newRun.run(args);
    }
}

