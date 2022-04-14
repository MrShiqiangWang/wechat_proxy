package com.wangsq.receiver;

import com.google.common.collect.Maps;
import com.wangsq.constant.AppConstant;
import com.wangsq.msg.WechatMsgService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 福泰
 * @version WechatMsgReceiveController.java, v 0.1 2022年04月13日 10:30 AM 福泰
 */
@RestController
public class WechatMsgReceiveController {

    @Autowired
    private WechatMsgService wechatMsgService;

    @Autowired
    private WxMpService wxMpService;

    @PostMapping(value = "wechat/msg")
    public ResponseEntity<Object> receiveWechatMsg(HttpServletRequest request, @RequestBody String body) {
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            return new ResponseEntity<>("非法请求", HttpStatus.FORBIDDEN);
        }
        Map<String, Object> requestMsg = Maps.newHashMap();
        requestMsg.put(AppConstant.SIGNATURE, signature);
        requestMsg.put(AppConstant.NONCE, nonce);
        requestMsg.put(AppConstant.TIMESTAMP, timestamp);
        requestMsg.put(AppConstant.BODY, body);

        String result = wechatMsgService.process(requestMsg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}