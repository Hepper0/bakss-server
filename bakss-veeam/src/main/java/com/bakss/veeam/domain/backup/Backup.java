package com.bakss.veeam.domain.backup;

import lombok.Data;

@Data
public class Backup {
    private String ID;
    private String jobId;
    private Integer targetType;
    private String repositoryId;
    private String creationTime;
    private String parentBackupId;
    private String isApplicationAwareProcessingEnabled;
    private String jobName;
    private String dirPath;
}
