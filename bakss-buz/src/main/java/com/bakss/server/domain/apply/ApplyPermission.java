package com.bakss.server.domain.apply;

import lombok.Data;

import java.util.Date;

@Data
public class ApplyPermission {

    private Integer appId;
    private String backupIds;
    private String applyUser;
    private String grantUser;
    private String expiration;
    private Date startTime;
    private Date endTime;
    private String remark;

}
