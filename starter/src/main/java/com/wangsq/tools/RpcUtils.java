package com.wangsq.tools;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 福泰
 * @version RpcUtils.java, v 0.1 2022年04月27日 14:47 福泰
 */
public class RpcUtils {

    public static final  MediaType JSON   = MediaType.get("application/json; charset=utf-8");
    private static final Logger    LOGGER = LoggerFactory.getLogger(RpcUtils.class);

    /**
     * http Get请求
     */
    public static String httpGet(String url, Map<String, String> headers, Map<String, Object> params) {
        OkHttpClient client = BeanUtils.getBean(OkHttpClient.class);
        url = formatUrl(url, params);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            LOGGER.error("请求失败", e);
        }
        return null;
    }

    /**
     * 构建URL请求
     *
     * @param url
     * @param params
     * @return
     */
    private static String formatUrl(String url, Map<String, Object> params) {
        StringBuilder builder = new StringBuilder();
        for (Entry<String, Object> entry : params.entrySet()) {
            builder.append(String.format("&%s=%s", entry.getKey(), entry.getValue()));
        }
        if (!url.endsWith("?")) {
            url = url + "?";
        }
        url = url + builder.toString();
        return url;
    }

    /**
     * http post请求
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String httpPost(String url, Map<String, String> headers, Map<String, Object> params) {
        OkHttpClient client = BeanUtils.getBean(OkHttpClient.class);
        RequestBody body = RequestBody.create(JSON, com.alibaba.fastjson.JSON.toJSONString(params));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            LOGGER.error("请求失败", e);
        }
        return null;
    }
}