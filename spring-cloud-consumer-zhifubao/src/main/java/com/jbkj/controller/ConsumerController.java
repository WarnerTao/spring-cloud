package com.jbkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 描述:调用提供者的 `home` 方法
 *
 * @author hekuangsheng
 * @create 2017-12-05 18:53
 **/
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultStores")
    @GetMapping(value = "/hello")
    public String hello() {
   //  return "ss";
      return restTemplate.getForEntity("http://EUREKA-USER/", String.class).getBody();
    }

    
    public String defaultStores() {
        return "Ribbon + hystrix Dashboard,EUREKA-USER ,提供者服务挂了";
    }
}
