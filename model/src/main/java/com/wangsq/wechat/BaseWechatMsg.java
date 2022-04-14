package com.wangsq.wechat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 福泰
 * @version BaseWechatMsg.java, v 0.1 2022年04月13日 10:44 AM 福泰
 */
@Setter
@Getter
public abstract class BaseWechatMsg {

    private String fromUser;

    private String toUser;

    private int type;
}