package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssApplyFlow;

/**
 * 申请流程Service接口
 * 
 * @author author
 * @date 2025-04-02
 */
public interface IBakssApplyFlowService 
{
    /**
     * 查询申请流程
     * 
     * @param id 申请流程主键
     * @return 申请流程
     */
    public BakssApplyFlow selectBakssApplyFlowById(Long id);

    /**
     * 查询申请流程列表
     * 
     * @param bakssApplyFlow 申请流程
     * @return 申请流程集合
     */
    public List<BakssApplyFlow> selectBakssApplyFlowList(BakssApplyFlow bakssApplyFlow);

    /**
     * 新增申请流程
     * 
     * @param bakssApplyFlow 申请流程
     * @return 结果
     */
    public int insertBakssApplyFlow(BakssApplyFlow bakssApplyFlow);

    /**
     * 修改申请流程
     * 
     * @param bakssApplyFlow 申请流程
     * @return 结果
     */
    public int updateBakssApplyFlow(BakssApplyFlow bakssApplyFlow);

    /**
     * 批量删除申请流程
     * 
     * @param ids 需要删除的申请流程主键集合
     * @return 结果
     */
    public int deleteBakssApplyFlowByIds(Long[] ids);

    /**
     * 删除申请流程信息
     * 
     * @param id 申请流程主键
     * @return 结果
     */
    public int deleteBakssApplyFlowById(Long id);
}
