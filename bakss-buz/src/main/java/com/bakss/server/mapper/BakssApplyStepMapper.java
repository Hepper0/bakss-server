package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssApplyStep;

/**
 * 申请步骤Mapper接口
 * 
 * @author author
 * @date 2025-04-06
 */
public interface BakssApplyStepMapper 
{
    /**
     * 查询申请步骤
     * 
     * @param id 申请步骤主键
     * @return 申请步骤
     */
    public BakssApplyStep selectBakssApplyStepById(Long id);

    public BakssApplyStep getBakssApplyStepByApplyType(Long applyType);

    /**
     * 查询申请步骤列表
     * 
     * @param bakssApplyStep 申请步骤
     * @return 申请步骤集合
     */
    public List<BakssApplyStep> selectBakssApplyStepList(BakssApplyStep bakssApplyStep);

    /**
     * 新增申请步骤
     * 
     * @param bakssApplyStep 申请步骤
     * @return 结果
     */
    public int insertBakssApplyStep(BakssApplyStep bakssApplyStep);

    /**
     * 修改申请步骤
     * 
     * @param bakssApplyStep 申请步骤
     * @return 结果
     */
    public int updateBakssApplyStep(BakssApplyStep bakssApplyStep);

    /**
     * 删除申请步骤
     * 
     * @param id 申请步骤主键
     * @return 结果
     */
    public int deleteBakssApplyStepById(Long id);

    /**
     * 批量删除申请步骤
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssApplyStepByIds(Long[] ids);
}
