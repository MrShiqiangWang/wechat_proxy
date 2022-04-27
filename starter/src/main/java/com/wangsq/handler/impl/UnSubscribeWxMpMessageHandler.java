package com.wangsq.handler.impl;

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
 * @version UnSubscribeWxMpMessageHandler.java, v 0.1 2022年04月27日 17:49 福泰
 */
public class UnSubscribeWxMpMessageHandler implements WechatWxMpMessageHandler<UserOutput> {
    @Autowired
    private UserProfileService userProfileService;

    @Override
    public void initRouterRule(WxMpMessageRouterRule rule) {
        rule.event(EventType.UNSUBSCRIBE);
    }

    @Override
    public WxMpXmlOutMessage digestMessage(WxMpXmlMessage message, UserOutput result) {
        return WxMpXmlOutMessage.TEXT().fromUser(result.getFromUser().getName()).toUser(result.getToUser().getName()).content(
                result.getMessage()).build();
    }

    @Override
    public UserOutput handleMsg(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                WxSessionManager sessionManager) {
        String fromUser = wxMessage.getFromUser();
        boolean unsubscribe = userProfileService.UnSubscribe(fromUser);
        return unsubscribeReply(wxMessage, unsubscribe);
    }

    /**
     * 退订返回
     *
     * @param wxMessage
     * @param unsubscribe
     * @return
     */
    private UserOutput unsubscribeReply(WxMpXmlMessage wxMessage, boolean unsubscribe) {
        UserOutput userOutput = new UserOutput();
        userOutput.setMessage("退订成功");
        return userOutput;
    }
}