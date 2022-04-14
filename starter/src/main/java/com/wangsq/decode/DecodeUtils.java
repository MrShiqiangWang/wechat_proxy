package com.wangsq.decode;

import com.wangsq.constant.AppConstant;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * @author 福泰
 * @version DecodeUtils.java, v 0.1 2022年04月14日 10:23 PM 福泰
 */
public class DecodeUtils {
    /**
     * 解析messag
     *
     * @param wxMpConfigStorage
     * @param requestMsg
     * @return
     */
    public static WxMpXmlMessage decode(WxMpConfigStorage wxMpConfigStorage, Map<String, Object> requestMsg) {
        String body = MapUtils.getString(requestMsg, AppConstant.BODY);
        String timestamp = MapUtils.getString(requestMsg, AppConstant.TIMESTAMP);
        String nonce = MapUtils.getString(requestMsg, AppConstant.NONCE);
        String signature = MapUtils.getString(requestMsg, AppConstant.SIGNATURE);
        return WxMpXmlMessage.fromEncryptedXml(body, wxMpConfigStorage, timestamp, nonce, signature);
    }
}