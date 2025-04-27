package com.bakss.veeam.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.VeeanExecError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;



public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static Response get(String URL, Map<String, String> headers, Map<String, Object> query) {
        try {
            String result = HttpRequest
                    .get(URL)
                    .headerMap(headers, true)
                    .form(query == null ? new HashMap<>() : query)
                    .execute()
                    .body();
            log.info("发送Get请求成功，url：{}，headers：{}，,query: {}, response：{}", URL, headers, query, result);
            return BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
        } catch (Exception e) {
            log.error("发送Get请求失败: ", e);
        }
        return null;
    }

    public static Response delete(String URL, Map<String, String> headers, Map<String, Object> query) {
        try {
            String result = HttpRequest
                    .delete(URL)
                    .headerMap(headers, true)
                    .form(query == null ? new HashMap<>() : query)
                    .execute()
                    .body();
            log.info("发送Delete请求成功，url：{}，headers：{}，,query: {}, response：{}", URL, headers, query, result);
            return BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
        } catch (Exception e) {
            log.error("发送Delete请求失败: ", e);
        }
        return null;
    }

    public static Response post(String URL, Map<String, String> headers, Map<String, Object> data) {
        try {
            String result = HttpRequest.post(URL).headerMap(headers, true).body(data.toString()).execute().body();
            log.info("发送Post请求成功,url: {}, headers: {}, data: {}, response: {}", URL, headers, data, result);
            Response response = BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
            if (!response.getCode().equals(0)) {
                throw new VeeanExecError("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("发送Post请求失败: ", e);
            // 远程端执行的错误要抛出去
            if (e.getClass() == VeeanExecError.class) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static Response put(String URL, Map<String, String> headers, Map<String, Object> data) {
        try {
            String result = HttpRequest.put(URL).headerMap(headers, true).body(data.toString()).execute().body();
            log.info("发送Put请求成功,url: {}, headers: {}, data: {}, response: {}", URL, headers, data, result);
            Response response = BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
            if (!response.getCode().equals(0)) {
                throw new VeeanExecError("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("发送Put请求失败: ", e);
            // 远程端执行的错误要抛出去
            if (e.getClass() == VeeanExecError.class) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
