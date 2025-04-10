package com.bakss.server.service.impl;

import java.util.Arrays;
import java.util.List;

import com.bakss.common.core.domain.entity.SysRole;
import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.server.domain.*;
import com.bakss.server.mapper.BakssAppFlowMapper;
import com.bakss.server.mapper.BakssAppStepMapper;
import com.bakss.server.service.IBakssBackupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssAppMapper;
import com.bakss.server.service.IBakssAppService;

import javax.annotation.Resource;

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

    final String APPROVAL_STATUS_LEADER = "leader";  // 直接上级审批
    final String APPROVAL_STATUS_OWNER = "owner";  // 直接上级审批
    final String APPROVAL_STATUS_ASSIGN = "assign";  // 直接上级审批
    final String APPROVAL_STATUS_DBA = "dba";  // DBA审批
    final String APPROVAL_STATUS_DBA_LEADER = "dbaLeader";  // DBA审批
    final String APPROVAL_STATUS_MANAGER = "admin"; // 备份管理员审批
    final Long APPROVAL_APPROVED = 1L;
    final Long APPROVAL_REJECTED = -1L;
    final int CREATE_RESTORE = 2;

    final List<String> DB_TYPES = Arrays.asList("MySQL", "SQLSERVER", "PostgreSQL", "Oracle");

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
    public List<BakssApp> selectBakssAppList(BakssApp bakssApp)
    {
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
        int id = bakssAppMapper.insertBakssApp(bakssApp);
        createFlows(bakssApp);
        return id;
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


    public void approved(BakssApp App){
        LoginUser user = SecurityUtils.getLoginUser();
        BakssAppFlow flow = bakssAppFlowMapper.selectBakssAppFlowById(App.getFlowId());
        flow.setReviewUser(user.getUsername());
        flow.setReviewStatus(APPROVAL_APPROVED);
        bakssAppFlowMapper.updateBakssAppFlow(flow);
        // 索引到下一步骤
        BakssAppFlow nextFlow = bakssAppFlowMapper.getBakssAppNextFlow(flow);
        if (nextFlow != null) {
            App.setFlowId(nextFlow.getId());
            bakssAppMapper.updateBakssApp(App);
        } else {
            log.warn("申请单[" + App.getId() + "]当前流程" + flow.getFlowStep() +  ", 找不到下一个流程.");
//            throw new RuntimeException("申请单[" + App.getId() + "]当前流程" + flow.getFlowStep() +  ", 找不到下一个流程.");
        }

    }

    public void rejected(BakssApp App) {
        BakssAppFlow flow = bakssAppFlowMapper.selectBakssAppFlowById(App.getFlowId());
        flow.setReviewStatus(APPROVAL_REJECTED);
        // todo 审核不通过的处理
    }

    public void createFlows(BakssApp App) {
        // todo 同时申请多个备份类型的不能这样关联
        BakssBackup backup = bakssBackupService.selectBakssBackupById(App.getBackupId());
        boolean isDB = DB_TYPES.contains(backup.getBackupContent());
        BakssAppStep bakssAppStep = bakssAppStepMapper.getBakssAppStepByAppType(App.getAppType(), isDB);
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
            flow.setAppId(App.getId());
            flow.setFlowStep(step);
            flow.setFlowOrder(i);
            flow.setReviewStatus(0L);
            // 默认索引到第一个流程
            if (flowId == -1) {
                flowId = bakssAppFlowMapper.insertBakssAppFlow(flow);
                App.setFlowId(flowId);
                bakssAppMapper.updateBakssApp(App);
            }
        }

    }
}
