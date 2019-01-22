package com.springcloud.serverauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/17 18:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("/loginUser")
public class UserInfoController {
    /**
     * 获取当前的用户
     * @return  完整的Authentication
     */
    @GetMapping("/me1")
    public Object currentUser() {
        //Spring框架借助ThreadLocal来保存和传递用户登录信息 获取保存在ThreadLocal中的用户信息
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/me2")
    public Object currentUser(Authentication authentication) {
        return authentication;
    }

    /**
     * @param userDetails
     * @return 只包含了userDetails
     */
    @GetMapping("/me3")
    public Object cuurentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
