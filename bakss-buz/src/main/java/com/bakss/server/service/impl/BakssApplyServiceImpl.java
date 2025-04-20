package com.bakss.server.service.impl;

import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.common.utils.uuid.IdUtils;
import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.BakssApplyBackupPermis;
import com.bakss.server.domain.apply.*;
import com.bakss.server.service.IBakssApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BakssApplyServiceImpl implements IBakssApplyService {

    Integer APPLY_BACKUP_PERMISSION = 0;
    Integer GRANT_BACKUP_PERMISSION = 1;
    Integer CREATE_RESTORE = 2;
    Integer CREATE_BACKUP = 3;
    Integer BACKUP_RIGHT_NOW = 4;
    Integer BACKUP_AT_TIME = 5;
    Integer MODIFY_DIRECTORY = 6;
    Integer ENABLE_STRATEGY = 7;
    Integer DISABLE_STRATEGY = 8;
    Integer DELETE_STRATEGY = 9;
    Integer MODIFY_OWNER = 10;
    Integer MODIFY_MANAGER = 11;

    @Resource
    private BakssAppServiceImpl appService;

    @Resource
    private BakssApplyBackupPermisServiceImpl applyBackupPermisService;

    // 申请 与 授权
    public void applyBackupPermission(ApplyPermission applyPermission) {

        List<String> backupIds = applyPermission.getBackupIds();
        String appId = addApplication(applyPermission, backupIds.stream().map(Object::toString).collect(Collectors.joining(",")));
        // 创建申请内容
        List<BakssApplyBackupPermis> bakssApplyBackupPermis = new ArrayList<>();
        for(String backupId : backupIds) {
            BakssApplyBackupPermis backupPermis = new BakssApplyBackupPermis();
            backupPermis.setBackupId(Long.getLong(backupId));
            backupPermis.setAppId(appId);
            backupPermis.setGrantUser(applyPermission.getGrantUser());
            backupPermis.setExpiration(applyPermission.getExpiration());
            backupPermis.setStartTime(applyPermission.getStartTime());
            backupPermis.setEndTime(applyPermission.getEndTime());
            bakssApplyBackupPermis.add(backupPermis);
        }
        applyBackupPermisService.batchInsertApplyBackupPermis(bakssApplyBackupPermis);
    }

    // 立即备份 与 定时备份
    public void applyBackupOnce(BakssApp bakssApp) {
        appService.insertBakssApp(bakssApp);
        appService.createFlows(bakssApp);
    }

    public void applyModifyBackupStrategy(ApplyStrategy strategy) {
        Integer appType = strategy.getAppType();
        if (appType.equals(ENABLE_STRATEGY)) {
            enableBackupStrategy(strategy);
        } else if (appType.equals(DISABLE_STRATEGY)) {
            disableBackupStrategy(strategy);
        } else if (appType.equals(DELETE_STRATEGY)) {
            deleteBackupStrategy(strategy);
        }
    }

    public String addApplication(ApplyBase apply, String backupId) {
        LoginUser user = SecurityUtils.getLoginUser();
        BakssApp app = new BakssApp();
        String appId = IdUtils.simpleUUID();
        app.setId(appId);
        app.setAppTime(DateUtils.getNowDate());
        app.setAppType(apply.getAppType());
        app.setAppUser(user.getUsername());
        app.setRemark(apply.getRemark());
        app.setBackupId(backupId);
        // 创建申请单
        appService.insertBakssApp(app);
        // 创建申请补助
        appService.createFlows(app);
        return appId;
    }

    // 修改目录
    public void addBackupModifyDirectory(ApplyModifyDirectory modifyDirectory) {
        String appId = addApplication(modifyDirectory, Integer.toString(modifyDirectory.getBackupId()));
        // todo addChangeBackupUser
    }

    // 启用策略
    public void enableBackupStrategy(ApplyStrategy strategy) {

    }

    // 禁用策略
    public void disableBackupStrategy(ApplyStrategy strategy) {

    }

    // 删除策略
    public void deleteBackupStrategy(ApplyStrategy strategy) {

    }

    // 修改负责人与管理员
    public void addBackupChangeUser(ApplyChangeUser changeUser) {
        String backupIds = changeUser.getBackupIds();
        String appId = addApplication(changeUser, backupIds);
        // todo addChangeBackupUser
    }
}
