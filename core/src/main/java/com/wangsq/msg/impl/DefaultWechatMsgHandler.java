package com.wangsq.msg.impl;

import com.wangsq.context.Context;
import com.wangsq.msg.WechatMsgHandler;
import com.wangsq.wechat.BaseWechatMsg;
import com.wangsq.wechat.BaseWechatResp;
import org.springframework.stereotype.Service;

/**
 * @author 福泰
 * @version DefaultWechatMsgHandler.java, v 0.1 2022年04月13日 1:58 PM 福泰
 */
@Service("DefaultWechatMsgHandler")
public class DefaultWechatMsgHandler implements WechatMsgHandler {
    @Override
    public BaseWechatResp handle(Context context, BaseWechatMsg msg) {
        // TODO: 2022/4/13  
        return null;
    }

    @Override
    public boolean isMatch(Context context, BaseWechatMsg msg) {
        return true;
    }
}