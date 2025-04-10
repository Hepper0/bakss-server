package com.bakss.server.service.impl;

import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.apply.ApplyPermission;

public class BakssApplyServiceImpl {

    Long APPLY_BACKUP_PERMISSION = 0L;
    Long GRANT_BACKUP_PERMISSION = 1L;
    Long CREATE_RESTORE = 2L;
    Long CREATE_BACKUP = 3L;
    Long BACKUP_RIGHT_NOW = 4L;
    Long BACKUP_AT_TIME = 5L;
    Long MODIFY_DIRECTORY = 6L;
    Long ENABLE_STRATEGY = 7L;
    Long DISABLE_STRATEGY = 8L;
    Long DELETE_STRATEGY = 9L;
    Long MODIFY_OWNER = 10L;
    Long MODIFY_MANAGER = 11L;

    public void addBackupPermissionApplication(ApplyPermission applyPermission) {
        String[] backupIds = applyPermission.getBackupIds().split(",");
        BakssApp app = new BakssApp();


    }
}
