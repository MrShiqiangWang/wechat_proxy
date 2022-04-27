package com.wangsq.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 福泰
 * @version AppConfig.java, v 0.1 2022年04月27日 14:50 福泰
 */
@Configuration
@ConfigurationProperties
@Setter
@Getter
public class AppConfig {

    @Value("com.wechatproxy.duolunUrl")
    private String duolunUrl;

    @Value("com.wechatproxy.intentUrl")
    private String intentUrl;

    @Value("com.wechatproxy.entityUrl")
    private String entityUrl;
}