package com.springcloud.serverauth.properties;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/18 15:22
 * @Description:
 */
public interface SecurityConstants {

    /**
     * 默认登录页面
     */
    String DEFAULT_LOGIN_PAGE_URL = "/authentication/require";

    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";


    /**
     * session失效默认的跳转地址
     */
    String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

    /**
     * 基础角色
     */
    String BASE_ROLE = "ROLE_USER";
}
