package com.springcloud.eurekaclinet2.controller;

import com.springcloud.eurekaclinet2.fegin.ProviderUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/14 9:58
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final ProviderUserService userService;

    @RequestMapping(value = "/providerUser/getByName")
    public String getUserByName(){
        return  userService.getUserByName();
    }

}
