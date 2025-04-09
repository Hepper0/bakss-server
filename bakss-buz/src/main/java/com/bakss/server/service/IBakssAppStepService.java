package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssAppStep;

/**
 * 申请步骤Service接口
 * 
 * @author author
 * @date 2025-04-06
 */
public interface IBakssAppStepService
{
    /**
     * 查询申请步骤
     * 
     * @param id 申请步骤主键
     * @return 申请步骤
     */
    public BakssAppStep selectBakssAppStepById(Long id);

    /**
     * 查询申请步骤列表
     * 
     * @param bakssAppStep 申请步骤
     * @return 申请步骤集合
     */
    public List<BakssAppStep> selectBakssAppStepList(BakssAppStep bakssAppStep);

    /**
     * 新增申请步骤
     * 
     * @param bakssAppStep 申请步骤
     * @return 结果
     */
    public int insertBakssAppStep(BakssAppStep bakssAppStep);

    /**
     * 修改申请步骤
     * 
     * @param bakssAppStep 申请步骤
     * @return 结果
     */
    public int updateBakssAppStep(BakssAppStep bakssAppStep);

    /**
     * 批量删除申请步骤
     * 
     * @param ids 需要删除的申请步骤主键集合
     * @return 结果
     */
    public int deleteBakssAppStepByIds(Long[] ids);

    /**
     * 删除申请步骤信息
     * 
     * @param id 申请步骤主键
     * @return 结果
     */
    public int deleteBakssAppStepById(Long id);
}
