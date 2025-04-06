package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyStepMapper;
import com.bakss.server.domain.BakssApplyStep;
import com.bakss.server.service.IBakssApplyStepService;

/**
 * 【请填写功能名称】Service业务层处理
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
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BakssApplyStep selectBakssApplyStepById(Long id)
    {
        return bakssApplyStepMapper.selectBakssApplyStepById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param bakssApplyStep 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BakssApplyStep> selectBakssApplyStepList(BakssApplyStep bakssApplyStep)
    {
        return bakssApplyStepMapper.selectBakssApplyStepList(bakssApplyStep);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param bakssApplyStep 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBakssApplyStep(BakssApplyStep bakssApplyStep)
    {
        bakssApplyStep.setCreateTime(DateUtils.getNowDate());
        return bakssApplyStepMapper.insertBakssApplyStep(bakssApplyStep);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param bakssApplyStep 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBakssApplyStep(BakssApplyStep bakssApplyStep)
    {
        bakssApplyStep.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyStepMapper.updateBakssApplyStep(bakssApplyStep);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyStepByIds(Long[] ids)
    {
        return bakssApplyStepMapper.deleteBakssApplyStepByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyStepById(Long id)
    {
        return bakssApplyStepMapper.deleteBakssApplyStepById(id);
    }
}
