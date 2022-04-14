package com.wangsq.factory;

import com.wangsq.wechat.BaseWechatMsg;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

/**
 * @author 福泰
 * @version WechatMsgHandler.java, v 0.1 2022年04月14日 8:27 PM 福泰
 */
public interface WechatMsgHandler {
    /**
     * 解析消息
     *
     * @param message
     * @return
     */
    BaseWechatMsg digest(WxMpXmlMessage message);

    /**
     * 是否匹配信息
     *
     * @param message
     * @return
     */
    boolean isMatch(WxMpXmlMessage message);
}