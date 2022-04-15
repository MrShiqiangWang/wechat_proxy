package com.wangsq.model.model.kefu;

import com.wangsq.model.model.userprofile.UserProfile;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 福泰
 * @version KefuResult.java, v 0.1 2022年04月15日 5:40 PM 福泰
 */
@Setter
@Getter
public class KefuResult {

    //发送者
    private UserProfile fromUser;

    //接收者
    private UserProfile toUser;

    //信息
    private String message;
}