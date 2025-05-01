package com.bakss.veeam.domain.backup;

import lombok.Data;

@Data
public class InstantRecovery {
    private String backupId;
    private String pointId;
    private Integer restoreMode;
    private Boolean restoreVMtags;
    private String vmName;
    private String host;
    private String vmFolder;
    private String resourcePool;
    private String datastoreName;
    private Boolean redirectWriteCache;
    private Boolean enableAntivirusScan;
    private String virusDetectionAction;
    private Boolean enableEntireVolumeScan;
    private String reason;
    private Boolean nicsEnabled;
    private Boolean powerUp;
}
