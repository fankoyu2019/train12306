package com.fanko.train.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @SentinelResource("hello")
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(500);
        return "Hello World! Business";}
}
