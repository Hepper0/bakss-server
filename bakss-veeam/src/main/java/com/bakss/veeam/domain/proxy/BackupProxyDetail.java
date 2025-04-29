package com.bakss.veeam.domain.proxy;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class BackupProxyDetail {
    private String ID;
    private Integer type;
    private String hostId;
    private Boolean disabled;
    private String name;
    private String options;
    private String description;
    private JSONObject veeamHost;
    private String optionsStruct;
    private String backupOptionsStruct;
    private String fileOptionsStruct;
    private String datastoreNames;
    private String serverName;
}
