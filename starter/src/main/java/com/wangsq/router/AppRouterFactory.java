package com.wangsq.router;

import com.wangsq.handler.WechatWxMpMessageHandler;
import com.wangsq.tools.BeanUtils;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import me.chanjar.weixin.mp.api.WxMpService;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 福泰
 * @version AppRouterFactory.java, v 0.1 2022年04月15日 2:25 PM 福泰
 */
public class AppRouterFactory {

    private static WxMpMessageRouter instance;

    /**
     * 创建路由
     *
     * @return
     */
    public static WxMpMessageRouter getRouter() {
        WxMpService wxMpService = BeanUtils.getBean(WxMpService.class);
        if (instance == null) {
            synchronized (WxMpMessageRouter.class) {
                if (instance == null) {
                    instance = new WxMpMessageRouter(wxMpService);
                    registerHandler(instance);
                }
            }
        }
        return instance;
    }

    /**
     * 注册处理器
     *
     * @param instance
     */
    private static void registerHandler(WxMpMessageRouter instance) {
        Map<String, WechatWxMpMessageHandler> handlerMap = BeanUtils.getBeanOfType(WechatWxMpMessageHandler.class);
        for (Entry<String, WechatWxMpMessageHandler> entry : handlerMap.entrySet()) {
            WxMpMessageRouterRule rule = instance.rule();
            WechatWxMpMessageHandler handler = entry.getValue();
            handler.initRouterRule(rule);
            rule.handler(handler).end();
        }
    }
}