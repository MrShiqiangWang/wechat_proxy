package com.wangsq.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * @author 福泰
 * @version WechatWxMpMessageHandler.java, v 0.1 2022年04月14日 10:00 PM 福泰
 */
public interface WechatWxMpMessageHandler<T> extends WxMpMessageHandler {

    /**
     * 初始化路由规则
     *
     * @param rule
     */
    void initRouterRule(WxMpMessageRouterRule rule);

    /**
     * 信息转化
     *
     * @param message
     * @param result
     * @return
     */
    WxMpXmlOutMessage digestMessage(WxMpXmlMessage message, T result);

    /**
     * 信息处理主干流程
     *
     * @param wxMessage
     * @param context
     * @param wxMpService
     * @param sessionManager
     * @return
     * @throws WxErrorException
     */
    @Override
    default WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                     WxSessionManager sessionManager) throws WxErrorException {
        T result = this.handleMsg(wxMessage, context, wxMpService, sessionManager);
        return this.digestMessage(wxMessage, result);
    }

    /**
     * 处理数据
     *
     * @param wxMessage
     * @param context
     * @param wxMpService
     * @param sessionManager
     * @return
     */
    T handleMsg(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager);
}