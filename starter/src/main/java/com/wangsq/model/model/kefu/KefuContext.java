package com.wangsq.model.model.kefu;

import com.wangsq.model.model.userprofile.UserProfile;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

/**
 * @author 福泰
 * @version KefuContext.java, v 0.1 2022年04月15日 5:38 PM 福泰
 */
@Setter
@Getter
public class KefuContext {

    //发送者
    private UserProfile fromUser;

    //接收者
    private UserProfile toUser;

    //消息内容
    private UserInput input;

    //输出
    private UserOutput result;

    //微信消息
    private WxMpXmlMessage message;

}