package com.wangsq.msg;

import com.wangsq.context.Context;
import com.wangsq.msg.impl.DefaultWechatMsgHandler;
import com.wangsq.tools.BeanUtils;
import com.wangsq.wechat.BaseWechatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 福泰
 * @version WechatMsgHandlerFactory.java, v 0.1 2022年04月13日 10:46 AM 福泰
 */
@Service
public class WechatMsgHandlerFactory {

    @Autowired
    private DefaultWechatMsgHandler defaultWechatMsgHandler;

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
        return defaultWechatMsgHandler;
    }
}