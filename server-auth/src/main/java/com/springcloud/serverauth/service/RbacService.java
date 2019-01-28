package com.springcloud.serverauth.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: YuanJinXiu
 * @Description: 判断是否有权限的接口
 * @Date: 2019/1/22 15:44
 * @Version: 1.0
 */
public interface  RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
