package com.springcloud.eurekaclinet2.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: YuanJinXiu
 * @Description: 调用eureka-client
 * @Date: 2019/1/14 9:54
 * @Version: 1.0
 */
@FeignClient("eureka-client")
public interface ProviderUserService {

    @RequestMapping(value = "/user/getByName")
    public String getUserByName ();
}
