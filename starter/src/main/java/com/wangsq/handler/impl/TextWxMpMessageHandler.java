package com.wangsq.handler.impl;

import com.wangsq.context.ContextService;
import com.wangsq.handler.WechatWxMpMessageHandler;
import com.wangsq.kefu.KefuService;
import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.model.model.kefu.KefuResult;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 福泰
 * @version TextWxMpMessageHandler.java, v 0.1 2022年04月15日 5:32 PM 福泰
 */
@Service
public class TextWxMpMessageHandler implements WechatWxMpMessageHandler<KefuResult> {
    @Autowired
    private KefuService kefuService;

    @Autowired
    private ContextService contextService;

    @Override
    public void initRouterRule(WxMpMessageRouterRule rule) {
        rule.msgType(XmlMsgType.TEXT);
    }

    @Override
    public WxMpXmlOutMessage digestMessage(WxMpXmlMessage message, KefuResult result) {
        return WxMpXmlOutMessage.TEXT().fromUser(result.getFromUser().getName()).toUser(result.getToUser().getName()).content(
                result.getMessage()).build();
    }

    @Override
    public KefuResult handleMsg(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                WxSessionManager sessionManager) {
        KefuContext kefuContext = contextService.setUpContext(wxMessage);
        return this.kefuService.process(kefuContext);
    }

}