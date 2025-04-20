package com.bakss.server.domain.apply;

import lombok.Data;

@Data
public class ApplyBase {
    private Integer appId;
    private Integer appType;
    private String appUser;
    private String remark;

}
