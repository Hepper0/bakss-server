package com.bakss.server.service.impl;

import java.util.Arrays;
import java.util.List;

import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.server.domain.*;
import com.bakss.server.mapper.BakssApplyFlowMapper;
import com.bakss.server.mapper.BakssApplyStepMapper;
import com.bakss.server.service.IBakssBackupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyMapper;
import com.bakss.server.service.IBakssApplyService;

import javax.annotation.Resource;

/**
 * 申请Service业务层处理
 *
 * @author author
 * @date 2025-03-26
 */
@Service
public class BakssApplyServiceImpl implements IBakssApplyService
{

    private static final Logger log = LoggerFactory.getLogger(BakssApplyServiceImpl.class);

    @Resource
    private BakssApplyMapper bakssApplyMapper;

    @Resource
    private BakssApplyFlowMapper bakssApplyFlowMapper;

    @Resource
    private BakssApplyStepMapper bakssApplyStepMapper;

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
    public BakssApply selectBakssApplyById(String id)
    {
        return bakssApplyMapper.selectBakssApplyById(id);
    }

    /**
     * 查询申请列表
     *
     * @param bakssApply 申请
     * @return 申请
     */
    @Override
    public List<BakssApply> selectBakssApplyList(BakssApply bakssApply)
    {
        return bakssApplyMapper.selectBakssApplyList(bakssApply);
    }

    /**
     * 新增申请
     *
     * @param bakssApply 申请
     * @return 结果
     */
    @Override
    public int insertBakssApply(BakssApply bakssApply)
    {
        bakssApply.setCreateTime(DateUtils.getNowDate());
        int id = bakssApplyMapper.insertBakssApply(bakssApply);
        createFlows(bakssApply);
        return id;
    }

    /**
     * 修改申请
     *
     * @param bakssApply 申请
     * @return 结果
     */
    @Override
    public int updateBakssApply(BakssApply bakssApply)
    {
        bakssApply.setUpdateTime(DateUtils.getNowDate());

        return bakssApplyMapper.updateBakssApply(bakssApply);
    }

    /**
     * 批量删除申请
     *
     * @param ids 需要删除的申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyByIds(String[] ids)
    {
        return bakssApplyMapper.deleteBakssApplyByIds(ids);
    }

    /**
     * 删除申请信息
     *
     * @param id 申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyById(String id)
    {
        return bakssApplyMapper.deleteBakssApplyById(id);
    }


    public void approved(BakssApply apply){
        LoginUser user = SecurityUtils.getLoginUser();
        BakssApplyFlow flow = bakssApplyFlowMapper.selectBakssApplyFlowById(apply.getFlowId());
        flow.setReviewUser(user.getUsername());
        flow.setReviewStatus(APPROVAL_APPROVED);
        bakssApplyFlowMapper.updateBakssApplyFlow(flow);
        // 索引到下一步骤
        BakssApplyFlow nextFlow = bakssApplyFlowMapper.getBakssApplyNextFlow(flow);
        if (nextFlow != null) {
            apply.setFlowId(nextFlow.getId());
            bakssApplyMapper.updateBakssApply(apply);
        } else {
            log.warn("申请单[" + apply.getId() + "]当前流程" + flow.getFlowStep() +  ", 找不到下一个流程.");
//            throw new RuntimeException("申请单[" + apply.getId() + "]当前流程" + flow.getFlowStep() +  ", 找不到下一个流程.");
        }

    }

    public void rejected(BakssApply apply) {
        BakssApplyFlow flow = bakssApplyFlowMapper.selectBakssApplyFlowById(apply.getFlowId());
        flow.setReviewStatus(APPROVAL_REJECTED);
        // todo 审核不通过的处理
    }

    public void createFlows(BakssApply apply) {
        BakssApplyStep bakssApplyStep = bakssApplyStepMapper.getBakssApplyStepByApplyType(apply.getApplyType());
        String steps = bakssApplyStep.getApplySteps();
        String[] stepArr = steps.split(",");
        // todo 同时申请多个备份类型的不能这样关联
        BakssBackup backup = bakssBackupService.selectBakssBackupById(apply.getBackupId());
        boolean isDB = DB_TYPES.contains(backup.getBackupContent());

        long flowId = -1;
        for(int i = 0; i< stepArr.length; i++) {
            String step = stepArr[i];
            if (apply.getApplyType() == CREATE_RESTORE) {
                // 创建恢复特殊处理，需要直接经理审批
                if (!isDB && step.contains("dba")) step = APPROVAL_STATUS_LEADER;
            } else {
                // 非数据库备份，跳过DBA审核
                if (!isDB && step.contains("dba")) break;
            }

            BakssApplyFlow flow = new BakssApplyFlow();
            flow.setApplyId(apply.getId());
            flow.setFlowStep(step);
            flow.setFlowOrder(i);
            flow.setReviewStatus(0L);
            // 默认索引到第一个流程
            if (flowId == -1) {
                flowId = bakssApplyFlowMapper.insertBakssApplyFlow(flow);
                apply.setFlowId(flowId);
                bakssApplyMapper.updateBakssApply(apply);
            }
        }

    }

    public List<BakssTask> todo(){
        return null;
    }

    public List<BakssTask> done(){
        return null;
    }
}
