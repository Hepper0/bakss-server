package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyStepMapper;
import com.bakss.server.domain.BakssApplyStep;
import com.bakss.server.service.IBakssApplyStepService;

/**
 * 申请步骤Service业务层处理
 *
 * @author author
 * @date 2025-04-06
 */
@Service
public class BakssApplyStepServiceImpl implements IBakssApplyStepService
{
    @Autowired
    private BakssApplyStepMapper bakssApplyStepMapper;

    /**
     * 查询申请步骤
     *
     * @param id 申请步骤主键
     * @return 申请步骤
     */
    @Override
    public BakssApplyStep selectBakssApplyStepById(Long id)
    {
        return bakssApplyStepMapper.selectBakssApplyStepById(id);
    }

    /**
     * 查询申请步骤列表
     *
     * @param bakssApplyStep 申请步骤
     * @return 申请步骤
     */
    @Override
    public List<BakssApplyStep> selectBakssApplyStepList(BakssApplyStep bakssApplyStep)
    {
        return bakssApplyStepMapper.selectBakssApplyStepList(bakssApplyStep);
    }

    /**
     * 新增申请步骤
     *
     * @param bakssApplyStep 申请步骤
     * @return 结果
     */
    @Override
    public int insertBakssApplyStep(BakssApplyStep bakssApplyStep)
    {
        bakssApplyStep.setCreateTime(DateUtils.getNowDate());
        return bakssApplyStepMapper.insertBakssApplyStep(bakssApplyStep);
    }

    /**
     * 修改申请步骤
     *
     * @param bakssApplyStep 申请步骤
     * @return 结果
     */
    @Override
    public int updateBakssApplyStep(BakssApplyStep bakssApplyStep)
    {
        bakssApplyStep.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyStepMapper.updateBakssApplyStep(bakssApplyStep);
    }

    /**
     * 批量删除申请步骤
     *
     * @param ids 需要删除的申请步骤主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyStepByIds(Long[] ids)
    {
        return bakssApplyStepMapper.deleteBakssApplyStepByIds(ids);
    }

    /**
     * 删除申请步骤信息
     *
     * @param id 申请步骤主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyStepById(Long id)
    {
        return bakssApplyStepMapper.deleteBakssApplyStepById(id);
    }
}
