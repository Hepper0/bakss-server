package com.bakss.veeam.domain;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class Response {
    Integer code;
    JSONObject data;
    String msg;
}
