package com.springcloud.serverauth.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.serverauth.entity.BaseResponse;
import com.springcloud.serverauth.properties.SecurityConstants;
import com.springcloud.serverauth.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/18 16:53
 * @Description: 登录失败回调处理
 */
@Component("myAuthenctiationFailureHandler")
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
        // 判断是用哪一种方式进行登录的
        // 如果是JSON，则返回JSON字符串；否则进行页面的跳转
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new BaseResponse(exception.getMessage())));
        }else{
            logger.info("hello login in error");
            super.setDefaultFailureUrl(SecurityConstants.DEFAULT_LOGIN_PAGE_URL+"?error");
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
