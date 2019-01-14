package com.springcloud.eurekaclient1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/14 9:41
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getByName")
    public String getUserByName(){
        return "yuanjinxiu";
    }
}
