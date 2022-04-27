package com.wangsq.service;

import com.wangsq.model.model.userprofile.UserProfile;

import java.util.Map;

/**
 * @author 福泰
 * @version UserProfileService.java, v 0.1 2022年04月27日 14:27 福泰
 */
public interface UserProfileService {
    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserProfile findUserById(String userId);

    /**
     * 用户订阅
     *
     * @param userId
     * @param params
     */
    boolean subscribe(String userId, Map<String, Object> params);

    /**
     * 用户退订
     *
     * @param userId
     */
    boolean UnSubscribe(String userId);
}