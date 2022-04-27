package com.wangsq.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author 福泰
 * @version BeanAutoConfiguration.java, v 0.1 2022年04月27日 17:06 福泰
 */
@Configuration
public class BeanAutoConfiguration {

    @Bean
    ConnectionPool connectionPool(@Value("com.wechatproxy.http.maxIdleConnections") int maxIdleConnections,
                                  @Value("com.wechatproxy.http.maxIdleConnections") long keepAliveDuration) {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MINUTES);
    }

    @Bean
    OkHttpClient okHttpClient(ConnectionPool pool) {
        return new OkHttpClient().newBuilder().connectionPool(pool).build();
    }
}