package com.wangsq.service;

import com.google.common.collect.Maps;
import com.wangsq.model.model.kefu.KefuContext;
import com.wangsq.tools.RpcUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * @author 福泰
 * @version RpcProcessor.java, v 0.1 2022年04月27日 15:06 福泰
 */
public interface RpcProcessor<T> {

    /**
     * rpc请求
     *
     * @param kefuContext
     * @return
     */
    Map<String, Object> setUpParams(KefuContext kefuContext);

    /**
     * rpc请求处理
     *
     * @param context
     * @return
     */
    default T process(KefuContext context) {
        //构建参数
        Map<String, Object> params = this.setUpParams(context);
        //构建请求头
        Map<String, String> headers = this.setUpHeaders(context, params);
        //请求上下文
        String url = this.getUrl(context);
        //方法
        String method = MapUtils.getString(params, "httpMethod");
        String finalResult = null;
        if (method.equals("POST")) {
            finalResult = RpcUtils.httpGet(url, headers, params);
        } else {
            finalResult = RpcUtils.httpPost(url, headers, params);
        }
        //解析最终结果
        return this.resolveResponse(context, params, finalResult);
    }

    /**
     * 解析最终结果
     *
     * @param context
     * @param params
     * @param finalResult
     * @return
     */
    T resolveResponse(KefuContext context, Map<String, Object> params, String finalResult);

    /**
     * 消息头
     *
     * @param context
     * @param params
     * @return
     */
    default Map<String, String> setUpHeaders(KefuContext context, Map<String, Object> params) {
        return Maps.newHashMap();
    }

    /**
     * 请求URL
     *
     * @param context
     * @return
     */
    String getUrl(KefuContext context);
}