package com.wangsq.handler;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;

/**
 * @author 福泰
 * @version WechatWxMpMessageHandler.java, v 0.1 2022年04月14日 10:00 PM 福泰
 */
public interface WechatWxMpMessageHandler extends WxMpMessageHandler {

    /**
     * 初始化路由规则
     *
     * @param rule
     */
    void initRouterRule(WxMpMessageRouterRule rule);
}