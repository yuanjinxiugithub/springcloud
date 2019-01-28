package com.springcloud.serverauth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yjx
 * @Date: Create in 2018/3/18 15:20
 * @Description:    可配置的属性
 */
@ConfigurationProperties(prefix = "my.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }


}
