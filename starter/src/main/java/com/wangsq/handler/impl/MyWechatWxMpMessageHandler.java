package com.wangsq.handler.impl;

import com.wangsq.handler.WechatWxMpMessageHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 福泰
 * @version MyWechatWxMpMessageHandler.java, v 0.1 2022年04月15日 11:35 AM 福泰
 */
@Service("MyWechatWxMpMessageHandler")
public class MyWechatWxMpMessageHandler implements WechatWxMpMessageHandler {
    @Override
    public void initRouterRule(WxMpMessageRouterRule rule) {
        // TODO: 2022/4/15
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        // TODO: 2022/4/15
        return null;
    }
}