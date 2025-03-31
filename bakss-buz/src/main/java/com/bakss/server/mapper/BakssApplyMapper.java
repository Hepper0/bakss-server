package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssApply;

/**
 * 申请Mapper接口
 * 
 * @author author
 * @date 2025-03-26
 */
public interface BakssApplyMapper 
{
    /**
     * 查询申请
     * 
     * @param id 申请主键
     * @return 申请
     */
    public BakssApply selectBakssApplyById(String id);

    /**
     * 查询申请列表
     * 
     * @param bakssApply 申请
     * @return 申请集合
     */
    public List<BakssApply> selectBakssApplyList(BakssApply bakssApply);

    /**
     * 新增申请
     * 
     * @param bakssApply 申请
     * @return 结果
     */
    public int insertBakssApply(BakssApply bakssApply);

    /**
     * 修改申请
     * 
     * @param bakssApply 申请
     * @return 结果
     */
    public int updateBakssApply(BakssApply bakssApply);

    /**
     * 删除申请
     * 
     * @param id 申请主键
     * @return 结果
     */
    public int deleteBakssApplyById(String id);

    /**
     * 批量删除申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssApplyByIds(String[] ids);
}
