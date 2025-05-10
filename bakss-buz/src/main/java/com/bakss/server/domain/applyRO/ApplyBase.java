package com.bakss.server.domain.applyRO;

import lombok.Data;

@Data
public class ApplyBase {
    private String appId;
    private Integer appType;
    private String appUser;
    private String remark;

}
