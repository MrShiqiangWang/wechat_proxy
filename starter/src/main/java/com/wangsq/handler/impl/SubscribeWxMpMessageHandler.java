package com.wangsq.handler.impl;

import com.google.common.collect.Maps;
import com.wangsq.handler.WechatWxMpMessageHandler;
import com.wangsq.model.model.kefu.UserOutput;
import com.wangsq.service.UserProfileService;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 福泰
 * @version SubscribeWxMpMessageHandler.java, v 0.1 2022年04月27日 17:49 福泰
 */
public class SubscribeWxMpMessageHandler implements WechatWxMpMessageHandler<UserOutput> {
    @Autowired
    private UserProfileService userProfileService;

    @Override
    public void initRouterRule(WxMpMessageRouterRule rule) {
        rule.event(EventType.SUBSCRIBE);
    }

    @Override
    public WxMpXmlOutMessage digestMessage(WxMpXmlMessage message, UserOutput result) {
        return WxMpXmlOutMessage.TEXT().fromUser(result.getFromUser().getName()).toUser(result.getToUser().getName()).content(
                result.getMessage()).build();
    }

    @Override
    public UserOutput handleMsg(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                WxSessionManager sessionManager) {
        Map<String, Object> subscribeMap = this.setUpSubscribeParams(wxMessage);
        boolean subscribe = userProfileService.subscribe(wxMessage.getFromUser(), subscribeMap);
        return subscribeReply(wxMessage, subscribe);
    }

    private UserOutput subscribeReply(WxMpXmlMessage wxMessage, boolean subscribe) {
        UserOutput output = new UserOutput();
        output.setMessage("欢迎订阅公众号，请多多指教。");
        return output;
    }

    /**
     * 订阅信息
     *
     * @param wxMessage
     * @return
     */
    private Map<String, Object> setUpSubscribeParams(WxMpXmlMessage wxMessage) {
        Map<String, Object> maps = Maps.newHashMap();
        maps.put("userId", wxMessage.getFromUser());
        return maps;
    }
}