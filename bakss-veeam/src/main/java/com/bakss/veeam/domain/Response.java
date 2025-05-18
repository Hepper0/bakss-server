package com.bakss.veeam.domain;

import lombok.Data;

@Data
public class Response {
    Integer code;
    Object data;
    String msg;
    Integer total;
    Integer page;
    Integer pageSize;
}
