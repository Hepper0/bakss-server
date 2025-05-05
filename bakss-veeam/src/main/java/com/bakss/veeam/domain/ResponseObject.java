package com.bakss.veeam.domain;


import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class ResponseObject extends Response {
    JSONObject data;
}
