package com.bakss.server.domain.apply;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApplyPermission {

    private Integer appId;
    private List<String> backupIds;
    private Integer appType;
    private String appUser;
    private String grantUser;
    private Integer expiration;
    private Date startTime;
    private Date endTime;
    private String remark;

}
