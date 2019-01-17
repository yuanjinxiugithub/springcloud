/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.springcloud.serverauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lengleng
 * @date 2018年03月10日
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    /**
     * 认证页面
     * @return ModelAndView
     */
    @RequestMapping("/require")
    public ModelAndView require() {
        return new ModelAndView("login");
    }

    /**
     * 用户信息校验
     * @param authentication 信息
     * @return 用户信息
     */
    @ResponseBody
    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @ResponseBody
    @RequestMapping("/hi")
    public String  hi(){
        return  "hi";
    }


}