package com.wangsq.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 福泰
 * @version WechatProxyApplication.java, v 0.1 2022年04月13日 10:25 AM 福泰
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = "com.wangsq")
public class WechatProxyApplication {

    public static void main(String[] args) {
        //启动spring服务
        SpringApplication.run(WechatProxyApplication.class, args);
    }
}