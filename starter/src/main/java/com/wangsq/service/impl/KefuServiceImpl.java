package com.wangsq.service.impl;

import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.model.model.kefu.UserOutput;
import com.wangsq.service.DuolunService;
import com.wangsq.service.KefuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 福泰
 * @version KefuServiceImpl.java, v 0.1 2022年04月27日 14:20 福泰
 */
@Service
public class KefuServiceImpl implements KefuService {

    @Autowired
    private DuolunService duolunService;

    @Override
    public UserOutput process(KefuContext kefuContext) {
        //调用多轮对话服务
        duolunService.process(kefuContext);
        return kefuContext.getResult();
    }
}