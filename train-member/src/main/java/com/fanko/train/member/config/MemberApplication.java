package com.fanko.train.member.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;


@SpringBootApplication
@ComponentScan("com.fanko")
public class MemberApplication {
    private static final Logger Log = LoggerFactory.getLogger(MemberApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        Environment env = app.run(args).getEnvironment();
        Log.info("启动成功!!");
        Log.info("测试地址:\thttp://127.0.0.1:{}/member/hello",env.getProperty("server.port"));
    }
}
