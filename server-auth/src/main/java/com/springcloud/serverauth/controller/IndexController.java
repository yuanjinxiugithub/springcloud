package com.springcloud.serverauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: YuanJinXiu
 * @Description: 系统首页
 * @Date: 2019/1/17 18:09
 * @Version: 1.0
 */
@Controller
public class IndexController {
    /**
     *  index页面
     */
    @RequestMapping("/index")
    public ModelAndView require() {
        return new ModelAndView("index");
    }
}
