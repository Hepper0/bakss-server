package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssAppStepMapper;
import com.bakss.server.domain.BakssAppStep;
import com.bakss.server.service.IBakssAppStepService;

/**
 * 申请步骤Service业务层处理
 *
 * @author author
 * @date 2025-04-06
 */
@Service
public class BakssAppStepServiceImpl implements IBakssAppStepService
{
    @Autowired
    private BakssAppStepMapper bakssAppStepMapper;

    /**
     * 查询申请步骤
     *
     * @param id 申请步骤主键
     * @return 申请步骤
     */
    @Override
    public BakssAppStep selectBakssAppStepById(Long id)
    {
        return bakssAppStepMapper.selectBakssAppStepById(id);
    }

    /**
     * 查询申请步骤列表
     *
     * @param bakssAppStep 申请步骤
     * @return 申请步骤
     */
    @Override
    public List<BakssAppStep> selectBakssAppStepList(BakssAppStep bakssAppStep)
    {
        return bakssAppStepMapper.selectBakssAppStepList(bakssAppStep);
    }

    /**
     * 新增申请步骤
     *
     * @param bakssAppStep 申请步骤
     * @return 结果
     */
    @Override
    public int insertBakssAppStep(BakssAppStep bakssAppStep)
    {
        bakssAppStep.setCreateTime(DateUtils.getNowDate());
        return bakssAppStepMapper.insertBakssAppStep(bakssAppStep);
    }

    /**
     * 修改申请步骤
     *
     * @param bakssAppStep 申请步骤
     * @return 结果
     */
    @Override
    public int updateBakssAppStep(BakssAppStep bakssAppStep)
    {
        bakssAppStep.setUpdateTime(DateUtils.getNowDate());
        return bakssAppStepMapper.updateBakssAppStep(bakssAppStep);
    }

    /**
     * 批量删除申请步骤
     *
     * @param ids 需要删除的申请步骤主键
     * @return 结果
     */
    @Override
    public int deleteBakssAppStepByIds(Long[] ids)
    {
        return bakssAppStepMapper.deleteBakssAppStepByIds(ids);
    }

    /**
     * 删除申请步骤信息
     *
     * @param id 申请步骤主键
     * @return 结果
     */
    @Override
    public int deleteBakssAppStepById(Long id)
    {
        return bakssAppStepMapper.deleteBakssAppStepById(id);
    }
}
