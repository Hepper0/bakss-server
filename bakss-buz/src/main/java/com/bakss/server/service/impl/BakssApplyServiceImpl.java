package com.bakss.server.service.impl;

import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.common.utils.StringUtils;
import com.bakss.common.utils.uuid.IdUtils;
import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.BakssApplyBackup;
import com.bakss.server.domain.BakssApplyBackupPermis;
import com.bakss.server.domain.apply.*;
import com.bakss.server.service.IBakssApplyService;
import com.bakss.veeam.service.VeeamJobService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.bakss.server.config.Config.*;

@Service
public class BakssApplyServiceImpl implements IBakssApplyService {

    @Resource
    private BakssAppServiceImpl appService;

    @Resource
    private VeeamJobService veeamJobService;

    @Resource
    private BakssApplyBackupPermisServiceImpl applyBackupPermisService;

    @Resource
    private BakssApplyBackupServiceImpl applyBackupService;

//    @PostConstruct
//    void init() {
//        veeamJobService.getJobDetail("123");
//    }

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
        LoginUser user = SecurityUtils.getLoginUser();
        String appId = IdUtils.simpleUUID();
        bakssApp.setId(appId);
        bakssApp.setAppTime(DateUtils.getNowDate());
        bakssApp.setAppUser(user.getUsername());
        appService.insertBakssApp(bakssApp);
        appService.createFlows(bakssApp);
    }

    public void applyModifyBackupStrategy(ApplyStrategy strategy) {
        String appId = addApplication(strategy, strategy.getBackupId().toString());
        strategy.setAppId(appId);
        Integer appType = strategy.getAppType();
        if (appType.equals(ENABLE_STRATEGY)) {
            enableBackupStrategy(strategy);
        } else if (appType.equals(DISABLE_STRATEGY)) {
            disableBackupStrategy(strategy);
        } else if (appType.equals(DELETE_STRATEGY)) {
            deleteBackupStrategy(strategy);
        }
    }

    // 修改目录
    public void applyModifyBackupDirectory(ApplyModifyDirectory modifyDirectory) {
        String appId = addApplication(modifyDirectory, Integer.toString(modifyDirectory.getBackupId()));
        // todo addChangeBackupUser
    }

    // 修改负责人与管理员
    public void applyChangeUser(ApplyChangeUser changeUser) {
        String backupIds = changeUser.getBackupIds();
        String appId = addApplication(changeUser, backupIds);
        // todo addChangeBackupUser
    }

    public void applyBackup(ApplyBackup applyBackup) {
        String appId = addApplication(applyBackup, null);
        BakssApplyBackup createBackup = new BakssApplyBackup();
        createBackup.setAppId(appId);
        createBackup.setName(applyBackup.getName());
        createBackup.setRepository(applyBackup.getRepository());
        createBackup.setAfterJob(applyBackup.getAfterJobName());
        createBackup.setVmObjects(StringUtils.join(applyBackup.getVmObjects(), ","));
        applyBackupService.insertBakssApplyBackup(createBackup);
    }

    public void applyRestore(ApplyRestore applyRestore) {
        String backupId = applyRestore.getBackupId().toString();
        String appId = addApplication(applyRestore, backupId);
        // todo createRestore
    }

    public void cancelApplication(String appId) {
        BakssApp app = new BakssApp();
        app.setId(appId);
        app.setStatus(CANCEL_APPLICATION);
        appService.updateBakssApp(app);
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
        if (backupId != null) app.setBackupId(backupId);
        // 创建申请单
        appService.insertBakssApp(app);
        // 创建申请步骤
        appService.createFlows(app);
        return appId;
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
}
