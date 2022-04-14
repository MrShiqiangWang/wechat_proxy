package com.wangsq.msg;

import java.util.Map;

/**
 * @author 福泰
 * @version WechatMsgService.java, v 0.1 2022年04月13日 10:37 AM 福泰
 */
public interface WechatMsgService {
    /**
     * 处理微信消息
     *
     * @param body
     * @return
     */
    String process(Map<String, Object> body);
}