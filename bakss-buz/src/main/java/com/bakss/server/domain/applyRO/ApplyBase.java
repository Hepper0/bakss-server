package com.bakss.server.domain.applyRO;

import lombok.Data;

@Data
public class ApplyBase {
    private String appId;
    private Integer appType;
    private String backupSoftware;
    private String backupServer;
    private String appUser;
    private String remark;
    private Boolean isDB;
}
