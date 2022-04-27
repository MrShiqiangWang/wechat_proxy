package com.wangsq.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangsq.config.AppConfig;
import com.wangsq.model.model.entity.IntentResult;
import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.service.IntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 福泰
 * @version IntentServiceImpl.java, v 0.1 2022年04月27日 15:21 福泰
 */
@Service
public class IntentServiceImpl implements IntentService {
    @Autowired
    private AppConfig appConfig;

    @Override
    public Map<String, Object> setUpParams(KefuContext kefuContext) {
        // TODO: 2022/4/27
        return null;
    }

    @Override
    public IntentResult resolveResponse(KefuContext context, Map<String, Object> params, String finalResult) {
        return JSON.parseObject(finalResult, IntentResult.class);
    }

    @Override
    public String getUrl(KefuContext context) {
        return appConfig.getIntentUrl();
    }
}