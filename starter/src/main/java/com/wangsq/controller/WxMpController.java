package com.wangsq.controller;

import com.google.common.collect.Maps;
import com.wangsq.constant.AppConstant;
import com.wangsq.msg.WechatMsgService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @version WxMpController.java, v 0.1 2022年04月13日 10:30 AM 福泰
 */
@RestController
public class WxMpController {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxMpController.class);

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
        //打印来自微信的消息内容
        LOGGER.info(String.format("接收到来自微信的消息:%s", requestMsg));
        //处理下消息
        String result = wechatMsgService.process(requestMsg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}