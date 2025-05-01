package com.bakss.veeam.domain.backup;

import lombok.Data;

@Data
public class ChildBackup {
    private String backupId;
    private String pointId;
    private String jobName;
    private Integer pointCount;
    private String creationTime;
    private Integer jobSourceType;
}
