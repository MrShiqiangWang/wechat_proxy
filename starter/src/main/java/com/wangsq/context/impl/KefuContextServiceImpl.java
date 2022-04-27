package com.wangsq.context.impl;

import com.wangsq.context.KefuContextService;
import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.model.model.kefu.UserInput;
import com.wangsq.model.model.userprofile.UserProfile;
import com.wangsq.service.UserProfileService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 福泰
 * @version KefuContextServiceImpl.java, v 0.1 2022年04月27日 14:22 福泰
 */
@Service
public class KefuContextServiceImpl implements KefuContextService {

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public KefuContext setUpContext(WxMpXmlMessage wxMessage) {
        KefuContext kefuContext = new KefuContext();

        //用户
        UserProfile fromUser = userProfileService.findUserById(wxMessage.getFromUser());
        kefuContext.setFromUser(fromUser);

        //用户
        UserProfile toUser = userProfileService.findUserById(wxMessage.getToUser());
        kefuContext.setToUser(toUser);

        //用户输入
        String content = wxMessage.getContent();
        UserInput input = new UserInput();
        input.setWords(content);
        kefuContext.setInput(input);

        //微信信息
        kefuContext.setMessage(wxMessage);

        return kefuContext;
    }
}