package com.jbkj.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 描述:调用提供者的 `home` 方法
 *
 * @author hekuangsheng
 * @create 2017-12-06 15:26
 **/
@RestController
public class ConsumerController {

    @Autowired
    private HomeClient homeClient;

    
    @HystrixCommand(fallbackMethod = "defaultStores")
    @GetMapping(value = "/hello")
    public String hello() {
        return  homeClient.consumer();
    }
    
    
    public String defaultStores() {
        return "fegin + hystrix Dashboard,EUREKA-USER ,提供者服务挂了";
    }
}
