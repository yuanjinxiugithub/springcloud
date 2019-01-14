package com.springcloud.eurekaclient1.interceptor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/14 11:24
 * @Version: 1.0
 */
@Component
public class MyHealthChecker implements HealthIndicator {
    private boolean up = true;

    private  static final String VERSION = "v1.0.0";
    @Override
    public Health health() {
        int code = 0;
        if(code != 0){
            Health.down().withDetail("code",code).withDetail("version",VERSION).build();
        }
        return Health.up().withDetail("code",code).withDetail("version",VERSION).up().build();
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
