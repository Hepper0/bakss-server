package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssApply;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author author
 * @date 2025-03-26
 */
public interface BakssApplyMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BakssApply selectBakssApplyById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bakssApply 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BakssApply> selectBakssApplyList(BakssApply bakssApply);

    /**
     * 新增【请填写功能名称】
     * 
     * @param bakssApply 【请填写功能名称】
     * @return 结果
     */
    public int insertBakssApply(BakssApply bakssApply);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bakssApply 【请填写功能名称】
     * @return 结果
     */
    public int updateBakssApply(BakssApply bakssApply);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBakssApplyById(String id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssApplyByIds(String[] ids);
}
