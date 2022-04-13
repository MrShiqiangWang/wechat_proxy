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

    /**
     * 填充用户信息
     *
     * @param context
     * @param msg
     */
    private void fillUserInfo(Context context, BaseWechatMsg msg) {
        List<CompletionStage<Pair<String, Boolean>>> stages = Lists.newArrayList();
        stages.add(fillUser(context, msg.getFromUser()));
        stages.add(fillUser(context, msg.getToUser()));
        CompletionStage<List<Pair<String, Boolean>>> batchThreads = WrapThreadUtils.batchExecuteThreads(stages);
        try {
            batchThreads.toCompletableFuture().get(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.error("批量执行失败", e);
        }
    }

    /**
     * 填充用户信息
     *
     * @param context
     * @param user
     * @return
     */
    public CompletionStage<Pair<String, Boolean>> fillUser(Context context, String user) {
        return ThreadUtils.supply(() -> {
            if (StringUtils.isNotEmpty(user)) {
                UserProfile fromUserProfile = userProfileService.queryUserById(user);
                context.setFromUser(fromUserProfile);
            }
            return Pair.of("fromUser", true);
        });
    }
}