package com.wangsq.context;

import com.wangsq.model.model.kefu.KefuContext;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

/**
 * @author 福泰
 * @version KefuContextService.java, v 0.1 2022年04月15日 5:37 PM 福泰
 */
public interface KefuContextService {
    /**
     * 创建上下文
     *
     * @param wxMessage
     * @return
     */
    KefuContext setUpContext(WxMpXmlMessage wxMessage);
}