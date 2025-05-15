package com.bakss.veeam.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.framework.web.domain.server.Sys;
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

    private final static Integer REQUEST_TIME = 10000;

    public static Response get(String URL, Map<String, String> headers, Map<String, Object> query) {
        return get(URL, headers, query, false);
    }

    public static Response get(String URL, Map<String, String> headers, Map<String, Object> query, Boolean isList) {
        try {
            log.info("send get,url:{},headers:{},,query: {},", URL, headers, query);
            String result = HttpRequest
                    .get(URL)
                    .headerMap(headers, true)
                    .form(query == null ? new HashMap<>() : query)
                    .timeout(REQUEST_TIME)
                    .execute()
                    .body();
            log.info("get successfully,url:{}, response:{}", URL, result);
            assert result != null : URL + "请求返回为空";
            Response response;
            if (isList) {
                response = BeanUtils.mapToBean(JSONObject.parseObject(result), ResponseList.class);
            } else {
                response = BeanUtils.mapToBean(JSONObject.parseObject(result), ResponseObject.class);
            }
            if (response.getCode() != 0) {
                throw new RuntimeException("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;

        } catch (Exception e) {
            log.error("send get failed", e);
            throw e;
        }
    }

    public static Response delete(String URL, Map<String, String> headers, Map<String, Object> query) {
        try {
            log.info("send delete,url:{}, headers:{}, query: {},", URL, headers, query);
            String result = HttpRequest
                    .delete(URL)
                    .headerMap(headers, true)
                    .form(query == null ? new HashMap<>() : query)
                    .timeout(REQUEST_TIME)
                    .execute()
                    .body();
            assert result != null : URL + "请求返回为空";
            log.info("delete successfully, url:{},  response:{}", URL, result);
            Response response = BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
            if (response.getCode() != 0) {
                throw new RuntimeException("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("send delete failed", e);
            throw e;
        }
    }

    public static Response post(String URL, Map<String, String> headers, Map<String, Object> data) {
        try {
            log.info("send post, url:{}, headers:{}, query: {},", URL, headers, data);
            String result = HttpRequest
                    .post(URL)
                    .headerMap(headers, true)
                    .body(JSONObject.toJSONString(data))
                    .timeout(REQUEST_TIME)
                    .execute()
                    .body();
            assert result != null : URL + "请求返回为空";
            log.info("post successfully, url: {}, response: {}", URL,  result);
            Response response = BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
            if (!response.getCode().equals(0)) {
                throw new RuntimeException("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("send post failed", e);
            // 远程端执行的错误要抛出去
            throw e;
        }
    }

    public static Response put(String URL, Map<String, String> headers, Map<String, Object> data) {
        try {
            log.info("send put,url:{}, headers:{}, query: {},", URL, headers, data);
            String result = HttpRequest
                    .put(URL)
                    .headerMap(headers, true)
                    .body(JSONObject.toJSONString(data))
                    .timeout(REQUEST_TIME)
                    .execute()
                    .body();
            assert result != null : URL + "请求返回为空";
            log.info("put successfully, url: {}, response: {}", URL, result);
            Response response = BeanUtils.mapToBean(JSONObject.parseObject(result), Response.class);
            if (!response.getCode().equals(0)) {
                throw new RuntimeException("return code: " + response.getCode() + ", msg: " + response.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("send put failed", e);
            throw e;
            // 远程端执行的错误要抛出去
//            if (e.getClass() == VeeanExecError.class) {
//                throw new RuntimeException(e);
//            }
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        Map<String, Object> map = new HashMap<>();
    }
}
