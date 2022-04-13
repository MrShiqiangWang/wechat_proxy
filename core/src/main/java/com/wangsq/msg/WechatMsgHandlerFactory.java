package com.wangsq.msg;

import com.wangsq.context.Context;
import com.wangsq.tools.BeanUtils;
import com.wangsq.wechat.BaseWechatMsg;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 福泰
 * @version WechatMsgHandlerFactory.java, v 0.1 2022年04月13日 10:46 AM 福泰
 */
public class WechatMsgHandlerFactory {
    /**
     * wechat 处理工厂
     *
     * @param context
     * @param msg
     * @return
     */
    public WechatMsgHandler getHandler(Context context, BaseWechatMsg msg) {
        Map<String, WechatMsgHandler> wechatMsgHandlerMap = BeanUtils.getBeanOfType(WechatMsgHandler.class);
        for (Entry<String, WechatMsgHandler> entry : wechatMsgHandlerMap.entrySet()) {
            WechatMsgHandler handler = entry.getValue();
            if (handler.isMatch(context, msg)) {
                return handler;
            }
        }
        return null;
    }
}