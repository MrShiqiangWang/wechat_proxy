package com.wangsq.config;

import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 福泰
 * @version AppConfig.java, v 0.1 2022年04月14日 8:00 PM 福泰
 */
@Configuration
@EnableAutoConfiguration
public class AppConfig {

    /**
     * 微信公众号配置
     * @param appId
     * @param secret
     * @param token
     * @param aesKey
     * @return
     */
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(@Value("com.wechatproxy.appId") String appId, @Value("com.wechatproxy.secret") String secret,
                                               @Value("com.wechatproxy.token") String token,
                                               @Value("com.wechatproxy.aesKey") String aesKey) {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(appId); // 设置微信公众号的appid
        config.setSecret(secret); // 设置微信公众号的app corpSecret
        config.setToken(token); // 设置微信公众号的token
        config.setAesKey(aesKey); // 设置微信公众号的EncodingAESKey
        return config;
    }
}