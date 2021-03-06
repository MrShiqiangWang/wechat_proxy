package com.wangsq.router;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 福泰
 * @version AppRouterConfig.java, v 0.1 2022年04月14日 9:09 PM 福泰
 */
@Configuration
@EnableAutoConfiguration
public class AppRouterConfig {

    /**
     * 信息存储
     *
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
        config.setAppId(appId);
        config.setSecret(secret);
        config.setToken(token);
        config.setAesKey(aesKey);
        return config;
    }

    /**
     * 公众号服务
     *
     * @param wxMpConfigStorage
     * @return
     */
    @Bean
    public WxMpService wxMpService(WxMpConfigStorage wxMpConfigStorage) {
        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxService;
    }

    /**
     * 路由策略
     *
     * @param wxMpService
     * @return
     */
    @Bean
    public WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {
        return new WxMpMessageRouter(wxMpService);
    }
}