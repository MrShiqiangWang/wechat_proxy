package com.wangsq.factory;

import com.google.common.collect.Maps;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.apache.commons.collections.MapUtils;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 福泰
 * @version WechatMsgFactory.java, v 0.1 2022年04月14日 8:26 PM 福泰
 */
public class WechatMsgFactory {

    /**
     * 解析消息的结构
     */
    private static Map<String, WechatMsgHandler> wechatMsgHandlerMap = Maps.newHashMap();

    /**
     * 解析微信消息
     *
     * @param message
     * @return
     */
    public static WechatMsgHandler getWechatMsgHandler(WxMpXmlMessage message) {
        if (MapUtils.isEmpty(wechatMsgHandlerMap)) {
            initWechatMsgMap();
        }
        for (Entry<String, WechatMsgHandler> entry : wechatMsgHandlerMap.entrySet()) {
            WechatMsgHandler handler = entry.getValue();
            if (handler.isMatch(message)) {
                return handler;
            }
        }
        return null;
    }

    private static void initWechatMsgMap() {
        // TODO: 2022/4/14
    }
}