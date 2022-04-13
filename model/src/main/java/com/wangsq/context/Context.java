package com.wangsq.context;

import com.wangsq.business.UserProfile;
import com.wangsq.wechat.BaseWechatMsg;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 福泰
 * @version Context.java, v 0.1 2022年04月13日 10:50 AM 福泰
 */
@Setter
@Getter
public class Context {

    private UserProfile fromUser;

    private UserProfile toUser;

    private BaseWechatMsg msg;

    private Context() {

    }

    public static Context defaultContext() {
        return new Context();
    }
}