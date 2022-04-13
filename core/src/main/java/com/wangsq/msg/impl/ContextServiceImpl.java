package com.wangsq.msg.impl;

import com.google.common.collect.Lists;
import com.wangsq.business.UserProfile;
import com.wangsq.context.Context;
import com.wangsq.msg.ContextService;
import com.wangsq.tools.ThreadUtils;
import com.wangsq.tools.WrapThreadUtils;
import com.wangsq.user.UserProfileService;
import com.wangsq.wechat.BaseWechatMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * @author 福泰
 * @version ContextServiceImpl.java, v 0.1 2022年04月13日 11:27 AM 福泰
 */
@Service
public class ContextServiceImpl implements ContextService {

    private static final Logger             LOGGER = LoggerFactory.getLogger(ContextServiceImpl.class);
    @Autowired
    private              UserProfileService userProfileService;

    @Override
    public Context setUpContext(BaseWechatMsg msg, Object body) {
        Context context = Context.defaultContext();
        //填充用户信息
        this.fillUserInfo(context, msg);
        //填充信息
        context.setMsg(msg);
        return context;
    }

    private void fillUserInfo(Context context, BaseWechatMsg msg) {
        List<CompletionStage<Pair<String, Boolean>>> stages = Lists.newArrayList();
        stages.add(ThreadUtils.supply(() -> {
            String fromUser = msg.getFromUser();
            if (StringUtils.isNotEmpty(fromUser)) {
                UserProfile fromUserProfile = userProfileService.queryUserById(fromUser);
                context.setFromUser(fromUserProfile);
            }
            return Pair.of("fromUser", true);
        }));
        stages.add(ThreadUtils.supply(() -> {
            String toUser = msg.getToUser();
            if (StringUtils.isNotEmpty(toUser)) {
                UserProfile toUserProfile = userProfileService.queryUserById(toUser);
                context.setFromUser(toUserProfile);
            }
            return Pair.of("toUser", true);
        }));
        CompletionStage<List<Pair<String, Boolean>>> batchThreads = WrapThreadUtils.batchExecuteThreads(stages);
        try {
            batchThreads.toCompletableFuture().get(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.error("批量执行失败", e);
        }
    }
}