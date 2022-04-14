package com.wangsq.wechat;

import com.wangsq.resp.RespStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 福泰
 * @version BaseWechatResp.java, v 0.1 2022年04月13日 10:53 AM 福泰
 */
@Setter
@Getter
public class BaseWechatResp {

    private int    status;
    private String result;

    private BaseWechatResp(RespStatus status, String result) {
        this.status = status.getStatus();
        this.result = result;
    }

    /**
     * 默认的返回
     *
     * @return
     */
    public static BaseWechatResp defaultResp() {
        return new BaseWechatResp(RespStatus.UNKNOWN_EXCEPTION, "未能给出当前消息的处理结果");
    }
}