package com.wangsq.service.user;

import com.wangsq.model.model.userprofile.UserProfile;

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
}