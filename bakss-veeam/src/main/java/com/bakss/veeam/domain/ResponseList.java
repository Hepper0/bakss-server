package com.bakss.veeam.domain;

import com.alibaba.fastjson2.JSONArray;
import lombok.Data;

@Data
public class ResponseList extends Response{
    JSONArray data;
}
