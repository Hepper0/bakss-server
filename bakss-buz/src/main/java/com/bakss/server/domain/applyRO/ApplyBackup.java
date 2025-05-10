package com.bakss.server.domain.applyRO;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class ApplyBackup extends ApplyBase {

    private String name;

    private String description;

    private String backupContent;

    private String machineType;

    private String dataCenter;

    private String env;

    private String platform;

    private String appName;

    private String backupSoftware;

    private String backupIP;

    private String backupPort;

    private String backupServer;

    private JSONObject backupInfo;

    private String costType;

    private String costNumber;

}
