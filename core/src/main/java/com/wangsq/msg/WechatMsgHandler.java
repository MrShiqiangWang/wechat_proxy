package com.wangsq.msg;

import com.wangsq.context.Context;
import com.wangsq.wechat.BaseWechatMsg;
import com.wangsq.wechat.BaseWechatResp;

/**
 * @author 福泰
 * @version WechatMsgHandler.java, v 0.1 2022年04月13日 10:51 AM 福泰
 */
public interface WechatMsgHandler {
    /**
     * 工厂处理
     *
     * @param context
     * @param msg
     * @return
     */
    BaseWechatResp handle(Context context, BaseWechatMsg msg);

    /**
     * 是否匹配当前的handler
     *
     * @param context
     * @param msg
     * @return
     */
    boolean isMatch(Context context, BaseWechatMsg msg);
}