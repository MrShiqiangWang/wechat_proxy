package com.wangsq.msg.impl;

import com.wangsq.context.Context;
import com.wangsq.decode.DecodeUtils;
import com.wangsq.decode.EncodeUtils;
import com.wangsq.msg.ContextService;
import com.wangsq.msg.WechatMsgHandler;
import com.wangsq.msg.WechatMsgHandlerFactory;
import com.wangsq.msg.WechatMsgService;
import com.wangsq.wechat.BaseWechatMsg;
import com.wangsq.wechat.BaseWechatResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String process(Object body) {
        //解码
        BaseWechatMsg msg = DecodeUtils.decode(body);
        //构建上下文
        Context context = contextService.setUpContext(msg, body);
        //工厂获取处理器
        WechatMsgHandler handler = factory.getHandler(context, msg);
        //处理
        BaseWechatResp resp = handler.handle(context, msg);
        //编码返回
        return EncodeUtils.encode(resp);
    }

}