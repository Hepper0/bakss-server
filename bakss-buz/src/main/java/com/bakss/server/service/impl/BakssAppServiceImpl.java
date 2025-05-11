package com.bakss.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.server.domain.*;
import com.bakss.server.domain.backup.BakssApplyBackupVmware;
import com.bakss.server.mapper.BakssAppFlowMapper;
import com.bakss.server.mapper.BakssAppStepMapper;
import com.bakss.server.service.*;
import com.bakss.veeam.domain.host.ViEntity;
import com.bakss.veeam.service.VeeamHostService;
import com.bakss.veeam.service.VeeamJobService;
import com.bakss.veeam.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssAppMapper;

import javax.annotation.Resource;

import static com.bakss.server.config.Config.*;

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
    private VeeamJobService veeamJobService;

    @Resource
    private VeeamHostService veeamHostService;

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
            handleApprovedApplication(app);
        }
    }

    public void handleApprovedApplication(BakssApp app) {
        // 设置订单为完成状态
        app.setStatus(APPLICATION_COMPLETED);

        Integer appType = app.getAppType();
        if (appType.equals(APPLY_BACKUP_PERMISSION)) {

        } else if(appType.equals(GRANT_BACKUP_PERMISSION)) {

        } else if(appType.equals(CREATE_RESTORE)) {

        } else if(appType.equals(CREATE_BACKUP)) {
            BakssApplyBackup applyBackup = applyBackupService.selectBakssApplyBackupByAppId(app.getId());
            BakssApplyBackupVmware applyBackupVmware = applyBackupVmwareService.selectBakssApplyBackupVmwareByAppId(app.getId());
            String vmObjects = applyBackupVmware.getVmObjects();
            List<ViEntity> vmEntities = new ArrayList<>();
            // 查询实时的vm信息
            for (String vm : vmObjects.split(",")) {
                ViEntity entity = veeamHostService.getViEntity(vm, "HostAndVms", applyBackup.getBackupServer());
                vmEntities.add(entity);
            }
            // 创建备份
            veeamJobService.createJob(applyBackup.getName(), applyBackup.getDescription(), vmEntities, applyBackupVmware.getRepository(), applyBackupVmware.getAfterJob(), applyBackup.getBackupServer());

            BakssBackup bakssBackup = BeanUtils.conventTo(applyBackup, BakssBackup.class);
            BakssBackupVmware bakssBackupVmware = BeanUtils.conventTo(applyBackupVmware, BakssBackupVmware.class);
            String backupId = bakssBackupService.insertBakssBackup(bakssBackup);
            bakssBackupVmware.setBackupId(backupId);
            bakssBackupVmwareService.insertBakssBackupVmware(bakssBackupVmware);

            app.setBackupId(backupId); // 申请单中关联备份任务执行状况

        } else if(appType.equals(BACKUP_RIGHT_NOW)) {

        } else if(appType.equals(BACKUP_AT_TIME)) {

        } else if(appType.equals(MODIFY_DIRECTORY)) {

        } else if(appType.equals(ENABLE_STRATEGY)) {

        } else if(appType.equals(DISABLE_STRATEGY)) {

        } else if(appType.equals(DELETE_STRATEGY)) {

        } else if(appType.equals(MODIFY_OWNER)) {

        } else if(appType.equals(MODIFY_MANAGER)) {

        }
        bakssAppMapper.updateBakssApp(app);
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
            BakssBackup backup = bakssBackupService.selectBakssBackupById(Long.valueOf(backupId));
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
