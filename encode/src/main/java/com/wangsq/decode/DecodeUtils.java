package com.wangsq.decode;

import com.wangsq.constant.AppConstant;
import com.wangsq.factory.WechatMsgFactory;
import com.wangsq.factory.WechatMsgHandler;
import com.wangsq.wechat.BaseWechatMsg;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * @author 福泰
 * @version DecodeUtils.java, v 0.1 2022年04月14日 7:50 PM 福泰
 */
public class DecodeUtils {
    /**
     * 解析微信的信息
     *
     * @param wxMpConfigStorage
     * @param requestMsg
     * @return
     */
    public static BaseWechatMsg decode(WxMpConfigStorage wxMpConfigStorage, Map<String, Object> requestMsg) {
        String timestamp = MapUtils.getString(requestMsg, AppConstant.TIMESTAMP);
        String signature = MapUtils.getString(requestMsg, AppConstant.SIGNATURE);
        String nonce = MapUtils.getString(requestMsg, AppConstant.NONCE);
        String body = MapUtils.getString(requestMsg, AppConstant.BODY);

        WxMpXmlMessage message = WxMpXmlMessage.fromEncryptedXml(body, wxMpConfigStorage, timestamp, nonce, signature);

        return parseMessage(message);
    }

    /**
     * 解析消息
     *
     * @param message
     * @return
     */
    private static BaseWechatMsg parseMessage(WxMpXmlMessage message) {
        WechatMsgHandler wechatMsgHandler = WechatMsgFactory.getWechatMsgHandler(message);
        return wechatMsgHandler.digest(message);
    }
}