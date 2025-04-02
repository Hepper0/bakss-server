package com.bakss.server.service.impl;

import java.util.Arrays;
import java.util.List;

import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.server.domain.BakssApplyFlow;
import com.bakss.server.domain.BakssBackup;
import com.bakss.server.domain.BakssTask;
import com.bakss.server.mapper.BakssApplyFlowMapper;
import com.bakss.server.service.IBakssBackupService;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyMapper;
import com.bakss.server.domain.BakssApply;
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
    @Resource
    private BakssApplyMapper bakssApplyMapper;

    @Resource
    private BakssApplyFlowMapper bakssApplyFlowMapper;

    @Resource
    private IBakssBackupService bakssBackupService;

    final int APPROVAL_STATUS_LEADER = 1;  // 直接上级审批
    final int APPROVAL_STATUS_DBA = 2;  // DBA审批
    final int APPROVAL_STATUS_MANAGER = 3; // 备份管理员审批
    final Long APPROVAL_APPROVED = 1L;
    final Long APPROVAL_REJECTED = -1L;

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
        return bakssApplyMapper.insertBakssApply(bakssApply);
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
        int nextStep;
        switch (flow.getFlowStep()) {
            case APPROVAL_STATUS_LEADER:
                // 查询当前用户是否是leader
                BakssBackup backup = bakssBackupService.selectBakssBackupById(apply.getBackupId());
                if (DB_TYPES.contains(backup.getBackupContent())) {
                    nextStep = APPROVAL_STATUS_DBA;
                } else {
                    nextStep = APPROVAL_STATUS_MANAGER;
                }
                flow.setReviewStatus(APPROVAL_APPROVED);
                addFlow(apply, nextStep);
                break;
            case APPROVAL_STATUS_DBA:
                flow.setReviewStatus(APPROVAL_APPROVED);
                addFlow(apply, APPROVAL_STATUS_MANAGER);
            case APPROVAL_STATUS_MANAGER:
                // 查询当前用户是否是备份管理员
                flow.setReviewStatus(APPROVAL_APPROVED);
                break;
        }
        bakssApplyFlowMapper.updateBakssApplyFlow(flow);
    }

    public void rejected(BakssApply apply) {
        BakssApplyFlow flow = bakssApplyFlowMapper.selectBakssApplyFlowById(apply.getFlowId());
        flow.setReviewStatus(APPROVAL_REJECTED);
    }

    public void addFlow(BakssApply apply, int step) {
        BakssApplyFlow flow = new BakssApplyFlow();
        flow.setApplyId(apply.getId());
        flow.setFlowStep(step);
        flow.setReviewStatus(0L);
        long flowId = bakssApplyFlowMapper.insertBakssApplyFlow(flow);
        apply.setFlowId(flowId);
        bakssApplyMapper.updateBakssApply(apply);
    }

    public List<BakssTask> todo(){
        return null;
    }

    public List<BakssTask> done(){
        return null;
    }
}
