package com.bakss.veeam.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.ResponseList;
import com.bakss.veeam.domain.ResponseObject;
import com.bakss.veeam.domain.VeeanExecError;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;



public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static Response get(String URL, Map<String, String> headers, Map<String, Object> query) {
        return get(URL, headers, query, false);
    }

    public static Response get(String URL, Map<String, String> headers, Map<String, Object> query, Boolean isList) {
        try {
            log.info("发送get请求，url：{}，headers：{}，,query: {},", URL, headers, query);
            String result = HttpRequest
                    .get(URL)
                    .headerMap(headers, true)
                    .form(query == null ? new HashMap<>() : query)
                    .execute()
                    .body();
            log.info("get请求成功，url：{}， response：{}", URL, result);
            assert result != null : URL + "请求返回为空";
            if (isList) {
                return BeanUtils.mapToBean(JSONObject.parseObject(result), ResponseList.class);
            } else {
                return BeanUtils.mapToBean(JSONObject.parseObject(result), ResponseObject.class);
            }

        } catch (Exception e) {
            log.error("发送get请求失败", e);
        }
        return null;
    }

    public static Response delete(String URL, Map<String, String> headers, Map<String, Object> query) {
        try {
            log.info("发送delete请求，url：{}，headers：{}，,query: {},", URL, headers, query);
            String result = HttpRequest
                    .delete(URL)
                    .headerMap(headers, true)
                    .form(query == null ? new HashMap<>() : query)
                    .execute()
                    .body();
            assert result != null : URL + "请求返回为空";
            log.info("delete请求成功，url：{}， response：{}", URL, result);
            return BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
        } catch (Exception e) {
            log.error("发送delete请求失败", e);
        }
        return null;
    }

    public static Response post(String URL, Map<String, String> headers, Map<String, Object> data) {
        try {
            log.info("发送post请求，url：{}，headers：{}，,query: {},", URL, headers, data);
            String result = HttpRequest.post(URL).headerMap(headers, true).body(data.toString()).execute().body();
            assert result != null : URL + "请求返回为空";
            log.info("post请求成功,url: {}, response: {}", URL,  result);
            Response response = BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
            if (!response.getCode().equals(0)) {
                throw new VeeanExecError("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("发送post请求失败", e);
            // 远程端执行的错误要抛出去
            if (e.getClass() == VeeanExecError.class) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static Response put(String URL, Map<String, String> headers, Map<String, Object> data) {
        try {
            log.info("发送put请求，url：{}，headers：{}，,query: {},", URL, headers, data);
            String result = HttpRequest.put(URL).headerMap(headers, true).body(data.toString()).execute().body();
            assert result != null : URL + "请求返回为空";
            log.info("put请求成功,url: {}, response: {}", URL, result);
            Response response = BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
            if (!response.getCode().equals(0)) {
                throw new VeeanExecError("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("发送put请求失败", e);
            // 远程端执行的错误要抛出去
            if (e.getClass() == VeeanExecError.class) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
