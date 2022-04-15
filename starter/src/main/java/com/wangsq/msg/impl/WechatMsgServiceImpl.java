package com.wangsq.msg.impl;

import com.wangsq.decode.DecodeUtils;
import com.wangsq.msg.WechatMsgService;
import com.wangsq.router.AppRouterFactory;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 福泰
 * @version WechatMsgServiceImpl.java, v 0.1 2022年04月13日 10:39 AM 福泰
 */
@Service
public class WechatMsgServiceImpl implements WechatMsgService {
    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    @Override
    public String process(Map<String, Object> requestMsg) {
        //解码
        WxMpXmlMessage msg = DecodeUtils.decode(wxMpConfigStorage, requestMsg);
        //路由器
        WxMpMessageRouter router = AppRouterFactory.getRouter();
        //构建上下文
        WxMpXmlOutMessage wxMpXmlOutMessage = router.route(msg);
        //编码返回
        return wxMpXmlOutMessage.toEncryptedXml(wxMpConfigStorage);
    }

}