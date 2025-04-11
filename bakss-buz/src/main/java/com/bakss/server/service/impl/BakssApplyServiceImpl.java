package com.bakss.server.service.impl;

import com.bakss.common.utils.DateUtils;
import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.BakssApplyBackupPermis;
import com.bakss.server.domain.apply.ApplyPermission;
import com.bakss.server.service.IBakssApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BakssApplyServiceImpl implements IBakssApplyService {

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

    @Resource
    private BakssAppServiceImpl appService;

    @Resource
    private BakssApplyBackupPermisServiceImpl applyBackupPermisService;

    // 申请 与 授权
    public void addBackupPermissionApplication(ApplyPermission applyPermission) {
//        LoginUser user = SecurityUtils.getLoginUser();
        String[] backupIds = applyPermission.getBackupIds().split(",");
        BakssApp app = new BakssApp();
        app.setAppTime(DateUtils.getNowDate());
        app.setAppType(applyPermission.getAppType());
        app.setAppUser(applyPermission.getApplyUser());
        // 创建申请单
        int appId = appService.insertBakssApp(app);
        // 创建申请补助
        appService.createFlows(app);
        // 创建申请内容
        List<BakssApplyBackupPermis> bakssApplyBackupPermis = new ArrayList<>();
        for(String backupId : backupIds) {
            BakssApplyBackupPermis backupPermis = new BakssApplyBackupPermis();
            backupPermis.setBackupId(Long.getLong(backupId));
            backupPermis.setAppId((long) appId);
            backupPermis.setGrantUser(applyPermission.getGrantUser());
            backupPermis.setExpiration(applyPermission.getExpiration());
            backupPermis.setStartTime(applyPermission.getStartTime());
            backupPermis.setEndTime(applyPermission.getEndTime());
            bakssApplyBackupPermis.add(backupPermis);
        }
        applyBackupPermisService.batchInsertApplyBackupPermis(bakssApplyBackupPermis);
    }

    // 立即备份 与 定时备份
    public void addBackupOnce(BakssApp bakssApp) {
        appService.insertBakssApp(bakssApp);
        appService.createFlows(bakssApp);
    }

    // 修改目录
    public void addBackupModifyDirectory() {

    }

    // 策略
    public void addBackupStrategy() {

    }

    // 修改负责人与管理员
    public void addBackupModifyUser() {

    }
}
