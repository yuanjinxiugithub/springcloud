package com.springcloud.eurekaclinet2.controller;

import com.springcloud.eurekaclinet2.interceptor.MyHealthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/14 11:27
 * @Version: 1.0
 */
@RestController
public class UpController {
    @Autowired
    MyHealthChecker myHealthChecker;

    @RequestMapping("/up")
    public String up(@RequestParam("up") Boolean up) {
        myHealthChecker.setUp(up);
        return up.toString();
    }

}
