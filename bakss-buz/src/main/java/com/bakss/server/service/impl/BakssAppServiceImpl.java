package com.bakss.server.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.core.redis.RedisCache;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.server.domain.*;
import com.bakss.server.domain.backup.BakssApplyBackupVmware;
import com.bakss.server.mapper.BakssAppFlowMapper;
import com.bakss.server.mapper.BakssAppStepMapper;
import com.bakss.server.service.*;
import com.bakss.veeam.domain.host.ViEntity;
import com.bakss.veeam.domain.job.ApplyBackupJob;
import com.bakss.veeam.domain.job.schedule.ApplyBackupJobScheduleDaily;
import com.bakss.veeam.service.VeeamJobService;
import com.bakss.veeam.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssAppMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.bakss.server.config.Config.*;
import static com.bakss.veeam.config.RedisConfig.*;

/**
 * 申请Service业务层处理
 *
 * @author author
 * @date 2025-03-26
 */
@Service
public class BakssAppServiceImpl implements IBakssAppService
{

    private static final Logger log = LoggerFactory.getLogger(BakssAppServiceImpl.class);

    @Resource
    private BakssAppMapper bakssAppMapper;

    @Resource
    private BakssAppFlowMapper bakssAppFlowMapper;

    @Resource
    private BakssAppStepMapper bakssAppStepMapper;

    @Resource
    private IBakssBackupService bakssBackupService;

    @Resource
    private IBakssBackupVmwareService bakssBackupVmwareService;

    @Resource
    private IBakssApplyBackupService applyBackupService;

    @Resource
    private IBakssApplyBackupVmwareService applyBackupVmwareService;

    @Resource
    private IBakssApplyBackupPermisService applyBackupPermisService;

    @Resource
    private IBakssApplyStrategyService applyStrategyService;

    @Resource
    private IBakssBackupValidateService validateService;

    @Resource
    private VeeamJobService veeamJobService;

    @Resource
    private RedisCache redisCache;

    private final Integer HANDLE_INTERVAL = 1000 * 60 * 10;

    @PostConstruct
    public void handleApprovedApplication() {
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception ignored){}
        new Thread(() -> {
            while (true) {

                try {
                    List<BakssApp> pendingApprovalApps = bakssAppMapper.getPendingApprovalApp();
                    log.info("pending approval application size: " + pendingApprovalApps.size());
                    pendingApprovalApps.forEach(this::handleApprovedApplication);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(HANDLE_INTERVAL);
                } catch (Exception ignored){}
            }
        }).start();
        log.info("Approval thread has started!");
    }

    /**
     * 查询申请
     *
     * @param id 申请主键
     * @return 申请
     */
    @Override
    public BakssApp selectBakssAppById(String id)
    {
        return bakssAppMapper.selectBakssAppById(id);
    }

    /**
     * 查询申请列表
     *
     * @param bakssApp 申请
     * @return 申请
     */
    @Override
    public List<BakssTask> selectBakssAppList(BakssApp bakssApp)
    {
        LoginUser user = SecurityUtils.getLoginUser();
        if (!user.getUser().isAdmin()) {
            bakssApp.setAppUser(user.getUsername());
        }
        return bakssAppMapper.selectBakssAppList(bakssApp);
    }

    /**
     * 新增申请
     *
     * @param bakssApp 申请
     * @return 结果
     */
    @Override
    public int insertBakssApp(BakssApp bakssApp)
    {
        bakssApp.setCreateTime(DateUtils.getNowDate());
        return bakssAppMapper.insertBakssApp(bakssApp);
    }

    /**
     * 修改申请
     *
     * @param bakssApp 申请
     * @return 结果
     */
    @Override
    public int updateBakssApp(BakssApp bakssApp)
    {
        bakssApp.setUpdateTime(DateUtils.getNowDate());

        return bakssAppMapper.updateBakssApp(bakssApp);
    }

    /**
     * 批量删除申请
     *
     * @param ids 需要删除的申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssAppByIds(String[] ids)
    {
        return bakssAppMapper.deleteBakssAppByIds(ids);
    }

    /**
     * 删除申请信息
     *
     * @param id 申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssAppById(String id)
    {
        return bakssAppMapper.deleteBakssAppById(id);
    }

    public void approved(BakssApp app){
        // 更新审核人及审核时间
        LoginUser user = SecurityUtils.getLoginUser();
        BakssAppFlow flow = bakssAppFlowMapper.selectBakssAppFlowById(app.getFlowId());
        flow.setReviewUser(user.getUsername());
        flow.setReviewStatus(APPROVAL_APPROVED);
        flow.setReviewTime(DateUtils.getNowDate());
        bakssAppFlowMapper.updateBakssAppFlow(flow);

        BakssAppFlow nextFlow = bakssAppFlowMapper.getBakssAppNextFlow(flow);
        if (nextFlow != null) {
            // 更新当前步骤的创建时间
            nextFlow.setCreateTime(DateUtils.getNowDate());
            bakssAppFlowMapper.updateBakssAppFlow(nextFlow);
            // 索引到下一步骤
            app.setFlowId(nextFlow.getId());
            bakssAppMapper.updateBakssApp(app);
        } else {
            log.info("申请单[" + app.getId() + "]当前流程" + flow.getFlowStep() +  ", 找不到下一个流程. 审核完成");
            // 设置订单为完成状态
            app.setStatus(APPLICATION_COMPLETED);
            bakssAppMapper.updateBakssApp(app);
        }
    }

    // 审批通过后对各种类型申请的处理
    public void handleApprovedApplication(BakssApp app) {
        log.info("Handling: " + app.getId());
        try {
            Integer appType = app.getAppType();
            if (appType.equals(APPLY_BACKUP_PERMISSION) || appType.equals(GRANT_BACKUP_PERMISSION)) {
                BakssApplyBackupPermis applyBackupPermis = applyBackupPermisService.selectBakssApplyBackupPermisByAppId(app.getId());
                BakssBackupValidate validate = new BakssBackupValidate();
                validate.setBackupId(applyBackupPermis.getBackupId());
                validate.setExpType(applyBackupPermis.getExpiration());
                validate.setUsername(applyBackupPermis.getGrantUser());
                if (applyBackupPermis.getExpiration() == 2) {
                    validate.setStartDate(applyBackupPermis.getStartTime());
                    validate.setEndDate(applyBackupPermis.getEndTime());
                }
                validateService.insertBakssBackupValidate(validate);

            } else if(appType.equals(CREATE_RESTORE)) {

            } else if(appType.equals(CREATE_BACKUP)) {
                BakssApplyBackup applyBackup = applyBackupService.selectBakssApplyBackupByAppId(app.getId());
                BakssApplyBackupVmware applyBackupVmware = applyBackupVmwareService.selectBakssApplyBackupVmwareByAppId(app.getId()); // todo repository 与 afterJob要提出来方外面

                // 对接createJob
                ApplyBackupJob applyBackupJob = new ApplyBackupJob();
                applyBackupJob.setName(applyBackup.getAppName());
                applyBackupJob.setAfterJobName(applyBackupVmware.getAfterJob());
                applyBackupJob.setDescription(applyBackup.getDescription());
                applyBackupJob.setIsScheduleEnable(true); // todo 第一期默认true
                applyBackupJob.setRepository(applyBackupVmware.getRepository());
                applyBackupJob.setPolicy("Daily"); // todo 第一期默认Daily

                // 处理特定类型的字段
                switch (applyBackup.getBackupContent()) {
                    case "VMware":
                        // 获取entity
                        String vmObjects = applyBackupVmware.getVmObjects();
                        List<JSONObject> entityCache = redisCache.getCacheList(String.format("%s%s:%s:%s", REDIS_VEEAM_HOST_PREFIX, applyBackup.getBackupServer(), "entity", applyBackupVmware.getVCenter()));
                        List<JSONObject> vmEntitiesJSON = entityCache.stream().filter(e -> Arrays.asList(vmObjects.split(",")).contains(e.getString("id"))).collect(Collectors.toList());
                        List<ViEntity> vmEntities = vmEntitiesJSON.stream().map(v -> BeanUtils.mapToBean(v, ViEntity.class)).collect(Collectors.toList());
                        applyBackupJob.setVmObjects(vmEntities);
                        break;
                    case "MySQL":
                        break;
                    case "PostgreSQL":
                        break;
                    case "Oracle":
                        break;
                    case "SQL server":
                        break;
                    case "FileSystem":
                        break;
                }

                // 处理Daily
                ApplyBackupJobScheduleDaily scheduleDaily = new ApplyBackupJobScheduleDaily();
                scheduleDaily.setStartDateTimeLocal(applyBackup.getScheduleTime());
                scheduleDaily.setDayNumberInMonth(applyBackup.getScheduleDateType());
                if (applyBackup.getScheduleDay() != null) {
                    scheduleDaily.setDayOfWeek(applyBackup.getScheduleDay().split(","));
                }
                applyBackupJob.setScheduleDaily(scheduleDaily);

                // 创建备份
                veeamJobService.createJob(applyBackupJob, applyBackup.getBackupServer());

                BakssBackup bakssBackup = BeanUtils.convertTo(applyBackup, BakssBackup.class);
//                BakssBackupVmware bakssBackupVmware = BeanUtils.convertTo(applyBackupVmware, BakssBackupVmware.class);

                bakssBackup.setBackupJobKey(applyBackup.getAppName()); // todo name是可以修改的， key修改为记录jobId
                String backupId = bakssBackupService.insertBakssBackup(bakssBackup);
//                bakssBackupVmware.setBackupId(backupId);
//                bakssBackupVmwareService.insertBakssBackupVmware(bakssBackupVmware);

                app.setBackupId(backupId); // 申请单中关联备份任务执行状况

            } else if(appType.equals(BACKUP_RIGHT_NOW)) {
                BakssBackup backup = bakssBackupService.selectBakssBackupById(app.getBackupId());
                veeamJobService.startJob(backup.getAppName() , app.getBackupServer());
            } else if(appType.equals(BACKUP_AT_TIME)) {
                BakssBackup backup = bakssBackupService.selectBakssBackupById(app.getBackupId());
                Date time = app.getBackupTime();
                // todo 添加定时任务
                veeamJobService.startJob(backup.getAppName() , app.getBackupServer());
            } else if(appType.equals(MODIFY_DIRECTORY)) {

            } else if(appType.equals(ENABLE_STRATEGY) || appType.equals(DISABLE_STRATEGY) || appType.equals(DELETE_STRATEGY)) {
                Long ENABLE = 1L;
                Long DISABLE = 2L;
                BakssApplyStrategy strategy = applyStrategyService.selectBakssApplyStrategyByAppId(app.getId());
                if (strategy.getType().equals(ENABLE)) {
                    veeamJobService.enableJob(strategy.getJobKey(), app.getBackupServer());
                } else if (strategy.getType().equals(DISABLE)) {
                    veeamJobService.disableJob(strategy.getJobKey(), app.getBackupServer());
                } else {
                    veeamJobService.deleteJob(strategy.getJobKey(), app.getBackupServer());
                }
            } else if(appType.equals(MODIFY_OWNER)) {

            } else if(appType.equals(MODIFY_MANAGER)) {

            }
            app.setIsCompleted(true);
            bakssAppMapper.updateBakssApp(app);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void rejected(BakssApp App) {
        LoginUser user = SecurityUtils.getLoginUser();
        BakssAppFlow flow = bakssAppFlowMapper.selectBakssAppFlowById(App.getFlowId());
        flow.setReviewStatus(APPROVAL_REJECTED);
        flow.setReviewUser(user.getUsername());
        flow.setReviewTime(DateUtils.getNowDate());
        // todo 审核不通过的处理
    }

    public void createFlows(BakssApp app) {
        boolean isDB;
        if (app.getIsDb() != null) {
            isDB = app.getIsDb();
        } else {
            String backupId = app.getBackupId().split(",")[0];
            BakssBackup backup = bakssBackupService.selectBakssBackupById(backupId);
            isDB = DB_TYPES.contains(backup.getBackupContent());
        }
        BakssAppStep bakssAppStep = bakssAppStepMapper.getBakssAppStepByAppType((long)app.getAppType(), isDB);
        String steps = bakssAppStep.getAppSteps();
        String[] stepArr = steps.split(",");

        long flowId = -1;
        for(int i = 0; i< stepArr.length; i++) {
            String step = stepArr[i];
//            if (App.getAppType() == CREATE_RESTORE) {
//                // 创建恢复特殊处理，需要直接经理审批
//                if (!isDB && step.contains("dba")) step = APPROVAL_STATUS_LEADER;
//            } else {
//                // 非数据库备份，跳过DBA审核
//                if (!isDB && step.contains("dba")) break;
//            }

            BakssAppFlow flow = new BakssAppFlow();
            flow.setAppId(app.getId());
            flow.setFlowStep(step);
            flow.setFlowOrder(i);
            flow.setReviewStatus(0L);
            bakssAppFlowMapper.insertBakssAppFlow(flow);
            // 默认索引到第一个流程
            if (flowId == -1) {
                flowId = flow.getId();
                app.setFlowId(flowId);
                bakssAppMapper.updateBakssApp(app);
            }
        }
    }
}
