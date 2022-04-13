package com.wangsq.msg;

import com.wangsq.context.Context;
import com.wangsq.wechat.BaseWechatMsg;

/**
 * @author 福泰
 * @version ContextService.java, v 0.1 2022年04月13日 10:51 AM 福泰
 */
public interface ContextService {
    /**
     * 构建上下文
     *
     * @param msg
     * @param body
     * @return
     */
    Context setUpContext(BaseWechatMsg msg, Object body);
}