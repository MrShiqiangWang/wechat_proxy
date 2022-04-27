package com.wangsq.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wangsq.config.AppConfig;
import com.wangsq.model.model.entity.DuolunResult;
import com.wangsq.model.model.entity.EntityResult;
import com.wangsq.model.model.entity.IntentResult;
import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.model.model.userprofile.UserProfile;
import com.wangsq.service.DuolunService;
import com.wangsq.service.EntityService;
import com.wangsq.service.IntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * @author 福泰
 * @version DuolunServiceImpl.java, v 0.1 2022年04月27日 14:47 福泰
 */
@Service
public class DuolunServiceImpl implements DuolunService {

    private static final String    WX_SCENCE_CODE = "WX";
    @Autowired
    private              AppConfig appConfig;

    @Autowired
    private IntentService intentService;

    @Autowired
    private EntityService entityService;

    @Override
    public Map<String, Object> setUpParams(KefuContext kefuContext) {
        UserProfile userProfile = kefuContext.getFromUser();
        String words = kefuContext.getInput().getWords();
        //解析实体
        EntityResult entityResult = entityService.process(kefuContext);
        //解析意图
        IntentResult intentResult = intentService.process(kefuContext);
        //参数
        Map<String, Object> params = Maps.newHashMap();
        params.put("topic", JSON.toJSONString(intentResult));
        params.put("query", words);
        params.put("sentenceEntities", JSON.toJSONString(entityResult));
        params.put("userProfile", JSON.toJSONString(userProfile));
        params.put("sceneCode", WX_SCENCE_CODE);
        params.put("uniqueCode", UUID.randomUUID().toString());
        params.put("traceId", UUID.randomUUID().toString());
        params.put("channel", "wechat");
        return params;
    }

    @Override
    public DuolunResult resolveResponse(KefuContext context, Map<String, Object> params, String finalResult) {
        return JSON.parseObject(finalResult, DuolunResult.class);
    }

    @Override
    public String getUrl(KefuContext context) {
        return appConfig.getDuolunUrl();
    }

}