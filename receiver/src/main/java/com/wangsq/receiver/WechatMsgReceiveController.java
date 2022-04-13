package com.wangsq.receiver;

import com.wangsq.msg.WechatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 福泰
 * @version WechatMsgReceiveController.java, v 0.1 2022年04月13日 10:30 AM 福泰
 */
@RestController
public class WechatMsgReceiveController {

    @Autowired
    private WechatMsgService wechatMsgService;

    @PostMapping(value = "wechat/msg")
    public ResponseEntity<Object> receiveWechatMsg(@RequestBody Object body) {
        String result = wechatMsgService.process(body);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}