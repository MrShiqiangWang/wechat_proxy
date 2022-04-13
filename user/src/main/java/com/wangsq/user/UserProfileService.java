package com.wangsq.user;

import com.wangsq.business.UserProfile;

/**
 * @author 福泰
 * @version UserProfileService.java, v 0.1 2022年04月13日 11:35 AM 福泰
 */
public interface UserProfileService {
    /**
     * 查询用户信息
     *
     * @param fromUser
     * @return
     */
    UserProfile queryUserById(String fromUser);
}