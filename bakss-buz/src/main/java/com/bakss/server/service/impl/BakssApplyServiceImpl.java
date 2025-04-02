package com.bakss.server.service.impl;

import java.util.Arrays;
import java.util.List;

import com.bakss.common.core.domain.entity.SysUser;
import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.server.domain.BakssBackup;
import com.bakss.server.mapper.BakssBackupMapper;
import com.bakss.server.service.IBakssBackupService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IBakssBackupService bakssBackupService;

    final int APPROVAL_STATUS_INIT = 0;
    final int APPROVAL_STATUS_LEADER = 1;  // 直接上级审批完成
    final int APPROVAL_STATUS_DBA = 2;  // DBA审批完成
    final int APPROVAL_STATUS_MANAGER = 3; // 备份管理员审批完成

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


    public void approve(BakssApply apply){
        LoginUser user = SecurityUtils.getLoginUser();
        int status = apply.getReviewStatus();
        switch (status) {
            case APPROVAL_STATUS_INIT:
                // 查询当前用户是否是leader
                apply.setReviewStatus(APPROVAL_STATUS_LEADER);
                break;
            case APPROVAL_STATUS_LEADER:
                // dba与备份管理员都进来看看
                BakssBackup backup = bakssBackupService.selectBakssBackupById(apply.getBackupId());
                if (DB_TYPES.contains(backup.getBackupContent())) {
                    apply.setReviewStatus(APPROVAL_STATUS_DBA);
                } else {
                    apply.setReviewStatus(APPROVAL_STATUS_MANAGER);
                }
                break;
            case APPROVAL_STATUS_DBA:
                // 查询当前用户是否是备份管理员
                apply.setReviewStatus(APPROVAL_STATUS_MANAGER);
                break;
        }

    }

    public void addFlow(BakssApply apply) {
        // todo 创建flow,并将flowId回写
    }
}
