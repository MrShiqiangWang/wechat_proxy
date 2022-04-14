package com.wangsq.resp;

import lombok.Getter;

/**
 * @author 福泰
 * @version RespStatus.java, v 0.1 2022年04月13日 2:02 PM 福泰
 */
@Getter
public enum RespStatus {

    SUCCESS(0, "成功返回"),
    UNKNOWN_EXCEPTION(1, "未知异常");
    private int    status;
    private String des;

    RespStatus(int status, String des) {
        this.status = status;
        this.des = des;
    }
}