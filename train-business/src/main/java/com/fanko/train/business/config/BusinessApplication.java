package com.fanko.train.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;


@SpringBootApplication
@ComponentScan("com.fanko")
@MapperScan("com.fanko.train.*.mapper")
@EnableFeignClients("com.fanko.train.business.feign")
@EnableCaching
public class BusinessApplication {
    private static final Logger Log = LoggerFactory.getLogger(BusinessApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        Log.info("启动成功!!");
        Log.info("测试地址:\thttp://127.0.0.1:{}{}/hello",env.getProperty("server.port"),env.getProperty("server.servlet.context-path"));
    }
}
