package com.wangsq.context.impl;

import com.wangsq.context.KefuContextService;
import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.model.model.userprofile.UserProfile;
import com.wangsq.service.user.UserProfileService;
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
        UserProfile fromUser = userProfileService.findUserById(wxMessage.getFromUser());
        UserProfile toUser = userProfileService.findUserById(wxMessage.getToUser());
        kefuContext.setFromUser(fromUser);
        kefuContext.setToUser(toUser);
        return kefuContext;
    }
}