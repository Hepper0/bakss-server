package com.bakss.server.domain;

import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

/**
 *  申请创建备份对象 bakss_apply_backup
 *
 * @author author
 * @date 2025-05-06
 */
@Data
public class BakssApplyBackup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String appId;

    private String name;

    private String description;

    private Long deleted;

    private String backupContent;

    private String machineType;

    private String dataCenter;

    private String env;

    private String platform;

    private String appName;

    private String backupSoftware;

    private String backupServer;
    private String backupIP;
    private String backupPort;

    private JSONObject backupInfo;

    private String costType;

    private String costNumber;

    private String isScheduleEnabled;

    private String policy;

    private String scheduleTime;

    private String scheduleDateType;

    private String scheduleDay;

}
