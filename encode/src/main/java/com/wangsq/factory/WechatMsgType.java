package com.wangsq.factory;

import lombok.Getter;

/**
 * @author 福泰
 * @version WechatMsgType.java, v 0.1 2022年04月14日 8:29 PM 福泰
 */
@Getter
public enum WechatMsgType {
    ;

    private String code;

    private String des;

    WechatMsgType(String code, String des) {
        this.code = code;
        this.des = des;
    }
}