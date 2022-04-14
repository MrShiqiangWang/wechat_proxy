package com.wangsq.msg;

import com.wangsq.context.Context;
import com.wangsq.wechat.BaseWechatResp;

/**
 * @author 福泰
 * @version WechatMsgSendService.java, v 0.1 2022年04月14日 7:56 PM 福泰
 */
public interface WechatMsgSendService {
    /**
     * 发送消息给到微信
     *
     * @param context
     * @param resp
     * @return
     */
    String sendMessage(Context context, BaseWechatResp resp);
}