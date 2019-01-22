package com.springcloud.serverauth.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.serverauth.entity.User;
import com.springcloud.serverauth.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yjx
 * @Date: Create in 2018/3/18 16:50
 * @Description:    登录成功回调处理
 */
@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        RequestCache requestCache = new HttpSessionRequestCache();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = null;
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            // 把authentication对象转成 json 格式 字符串 通过 response 以application/json;charset=UTF-8 格式写到响应里面去
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            //  跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
