package com.wangsq.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangsq.config.AppConfig;
import com.wangsq.model.model.entity.EntityResult;
import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 福泰
 * @version EntityServiceImpl.java, v 0.1 2022年04月27日 15:19 福泰
 */
@Service
public class EntityServiceImpl implements EntityService {
    @Autowired
    private AppConfig appConfig;

    @Override
    public Map<String, Object> setUpParams(KefuContext kefuContext) {
        // TODO: 2022/4/27  
        return null;
    }

    @Override
    public EntityResult resolveResponse(KefuContext context, Map<String, Object> params, String finalResult) {
        return JSON.parseObject(finalResult, EntityResult.class);
    }

    @Override
    public String getUrl(KefuContext context) {
        return appConfig.getEntityUrl();
    }
}