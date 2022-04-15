package com.wangsq.kefu;

import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.model.model.kefu.KefuResult;

/**
 * @author 福泰
 * @version KefuService.java, v 0.1 2022年04月15日 5:36 PM 福泰
 */
public interface KefuService {
    /**
     * 处理客服的请求
     *
     * @param kefuContext
     * @return
     */
    KefuResult process(KefuContext kefuContext);
}