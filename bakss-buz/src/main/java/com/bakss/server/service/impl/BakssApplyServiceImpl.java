package com.bakss.server.service.impl;

import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.common.utils.uuid.IdUtils;
import com.bakss.server.domain.*;
import com.bakss.server.domain.applyRO.*;
import com.bakss.server.domain.backup.*;
import com.bakss.server.service.IBakssApplyBackupVmwareService;
import com.bakss.server.service.IBakssApplyService;
import com.bakss.server.service.IBakssApplyStrategyService;
import com.bakss.server.service.IBakssBackupVmwareService;
import com.bakss.veeam.service.VeeamJobService;
import com.bakss.veeam.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bakss.server.config.Config.*;

@Service
public class BakssApplyServiceImpl implements IBakssApplyService {

    @Resource
    private BakssAppServiceImpl appService;

    @Resource
    private BakssApplyBackupPermisServiceImpl applyBackupPermisService;

    @Resource
    private BakssApplyBackupServiceImpl applyBackupService;

    @Resource
    private IBakssApplyBackupVmwareService applyBackupVmwareService;

    @Resource
    private IBakssApplyStrategyService applyStrategyService;

    private final String TYPE_VM = "WMware";
    private final String TYPE_MYSQL = "MySQL";
    private final String TYPE_POSTGRESQL = "PostgreSQL";
    private final String TYPE_SQLSERVER = "SQL SERVER";
    private final String TYPE_ORACLE = "Oracle";
    private final String TYPE_FILESYSTEM = "FileSystem";


    // 申请 与 授权
    public void applyBackupPermission(ApplyPermission applyPermission) {

        List<String> backupIds = applyPermission.getBackupIds();
        String appId = addApplication(applyPermission, backupIds.stream().map(Object::toString).collect(Collectors.joining(",")));
        // 创建申请内容
        List<BakssApplyBackupPermis> bakssApplyBackupPermis = new ArrayList<>();
        for(String backupId : backupIds) {
            BakssApplyBackupPermis backupPermis = new BakssApplyBackupPermis();
            backupPermis.setBackupId(backupId);
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
        String appId = addApplication(strategy, strategy.getBackupId());
        strategy.setAppId(appId);
        BakssApplyStrategy applyStrategy = BeanUtils.convertTo(strategy, BakssApplyStrategy.class);
        applyStrategyService.insertBakssApplyStrategy(applyStrategy);
    }

    // 修改目录
    public void applyModifyBackupDirectory(ApplyModifyDirectory modifyDirectory) {
        String appId = addApplication(modifyDirectory, modifyDirectory.getBackupId());
        // todo addChangeBackupUser
    }

    // 修改负责人与管理员
    public void applyChangeUser(ApplyChangeUser changeUser) {
        String backupIds = changeUser.getBackupIds();
        String appId = addApplication(changeUser, backupIds);
        // todo addChangeBackupUser
    }

    public void applyBackup(ApplyBackup applyBackup) {
        applyBackup.setIsDB(DB_TYPES.contains(applyBackup.getBackupContent()));
        String appId = addApplication(applyBackup, null);

        Map<String, Object> applyBackupJson = BeanUtils.beanToMap(applyBackup);
        BakssApplyBackup createBackup = BeanUtils.mapToBean(applyBackupJson, BakssApplyBackup.class);
        createBackup.setAppId(appId);
        applyBackupService.insertBakssApplyBackup(createBackup);
        switch (applyBackup.getBackupContent()) {
            case "VMware":
                BakssApplyBackupVmware vm = BeanUtils.mapToBean(createBackup.getBackupInfo(), BakssApplyBackupVmware.class);
                vm.setAppId(appId);
                applyBackupVmwareService.insertBakssApplyBackupVmware(vm);
                break;
            case "MySQL":
                MySQL mySQL = BeanUtils.mapToBean(createBackup.getBackupInfo(), MySQL.class);
                mySQL.setAppId(appId);
                break;
            case "PostgreSQL":
                PostgreSQL postgreSQL = BeanUtils.mapToBean(createBackup.getBackupInfo(), PostgreSQL.class);
                postgreSQL.setAppId(appId);
                break;
            case "Oracle":
                Oracle oracle = BeanUtils.mapToBean(createBackup.getBackupInfo(), Oracle.class);
                oracle.setAppId(appId);
                break;
            case "SQL server":
                SQLServer sqlServer = BeanUtils.mapToBean(createBackup.getBackupInfo(), SQLServer.class);
                sqlServer.setAppId(appId);
                break;
            case "FileSystem":
                FileSystem fileSystem = BeanUtils.mapToBean(createBackup.getBackupInfo(), FileSystem.class);
                fileSystem.setAppId(appId);
                break;
        }

    }

    public void applyRestore(ApplyRestore applyRestore) {
        String backupId = applyRestore.getBackupId().toString();
        String appId = addApplication(applyRestore, backupId);
        // todo createRestore
    }

    public void cancelApplication(String appId) {
        BakssApp app = new BakssApp();
        app.setId(appId);
        app.setStatus(APPLICATION_CANCELLED);
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
        app.setBackupSoftware(apply.getBackupSoftware());
        app.setIsDb(apply.getIsDB());
        app.setBackupServer(apply.getBackupServer());
        if (backupId != null) app.setBackupId(backupId);
        // 创建申请单
        appService.insertBakssApp(app);
        // 创建申请步骤
        appService.createFlows(app);
        return appId;
    }

}
