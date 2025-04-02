package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssApply;

/**
 * 申请Service接口
 * 
 * @author author
 * @date 2025-03-26
 */
public interface IBakssApplyService 
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
     * 批量删除申请
     * 
     * @param ids 需要删除的申请主键集合
     * @return 结果
     */
    public int deleteBakssApplyByIds(String[] ids);

    /**
     * 删除申请信息
     * 
     * @param id 申请主键
     * @return 结果
     */
    public int deleteBakssApplyById(String id);

    public void approve(BakssApply apply);
}
