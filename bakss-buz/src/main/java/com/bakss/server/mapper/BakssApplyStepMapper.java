package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssApplyStep;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author author
 * @date 2025-04-06
 */
public interface BakssApplyStepMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BakssApplyStep selectBakssApplyStepById(Long id);

    public BakssApplyStep getBakssApplyStepByApplyType(Long applyType);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bakssApplyStep 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BakssApplyStep> selectBakssApplyStepList(BakssApplyStep bakssApplyStep);

    /**
     * 新增【请填写功能名称】
     * 
     * @param bakssApplyStep 【请填写功能名称】
     * @return 结果
     */
    public int insertBakssApplyStep(BakssApplyStep bakssApplyStep);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bakssApplyStep 【请填写功能名称】
     * @return 结果
     */
    public int updateBakssApplyStep(BakssApplyStep bakssApplyStep);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBakssApplyStepById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssApplyStepByIds(Long[] ids);
}
