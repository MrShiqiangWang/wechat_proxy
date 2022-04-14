package com.wangsq.msg.impl;

import com.wangsq.context.Context;
import com.wangsq.decode.DecodeUtils;
import com.wangsq.msg.ContextService;
import com.wangsq.msg.WechatMsgHandler;
import com.wangsq.msg.WechatMsgHandlerFactory;
import com.wangsq.msg.WechatMsgSendService;
import com.wangsq.msg.WechatMsgService;
import com.wangsq.wechat.BaseWechatMsg;
import com.wangsq.wechat.BaseWechatResp;
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
    private WechatMsgHandlerFactory factory;
    @Autowired
    private ContextService          contextService;
    @Autowired
    private WechatMsgSendService    wechatMsgSendService;
    @Autowired
    private WxMpConfigStorage       wxMpConfigStorage;

    @Override
    public String process(Map<String, Object> requestMsg) {

        //解码
        BaseWechatMsg msg = DecodeUtils.decode(wxMpConfigStorage, requestMsg);
        //构建上下文
        Context context = contextService.setUpContext(msg, wxMpConfigStorage, requestMsg);
        //工厂获取处理器
        WechatMsgHandler handler = factory.getHandler(context, msg);
        //处理
        BaseWechatResp resp = handler.handle(context, msg);
        //编码返回
        return wechatMsgSendService.sendMessage(context, resp);
    }

}