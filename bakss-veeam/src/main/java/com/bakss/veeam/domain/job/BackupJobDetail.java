package com.bakss.veeam.domain.job;

import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.domain.host.ViEntity;
import lombok.Data;

import java.util.List;

@Data
public class BackupJobDetail {
    String id;
    String name;
    String description;
    List<ViEntity> selectedVmObjects;
    JSONObject backupProxyOptions;
    JSONObject jobOptions;
    Boolean keepFullBackupEnabled;
    JSONObject fullBackupGfsConfig;
    String backupRepositoryName;
    Boolean applicationAwareEnabled; //
    JSONObject guestInteractionProxy;
    String defaultGuestOSCred;
    List<Object> individualCreds;
    JSONObject schedule;
    Boolean retrySpecified;
    Integer retryTimes;
    Integer retryTimeout;
    JSONObject terminateOutWindow;
    Boolean terminateOutWindowEnabled;
    List<Object> applicationAwareOptions;
    JSONObject advancedConfig;
}
