package com.bakss.server.domain.applyRO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApplyPermission extends ApplyBase {

    private List<String> backupIds;
    private String grantUser;
    private Integer expiration;
    private Date startTime;
    private Date endTime;
}
