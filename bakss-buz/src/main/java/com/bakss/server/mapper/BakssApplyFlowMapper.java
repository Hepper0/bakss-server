package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssApplyFlow;

/**
 * 申请流程Mapper接口
 * 
 * @author author
 * @date 2025-04-02
 */
public interface BakssApplyFlowMapper 
{
    /**
     * 查询申请流程
     * 
     * @param id 申请流程主键
     * @return 申请流程
     */
    public BakssApplyFlow selectBakssApplyFlowById(Long id);

    public BakssApplyFlow getBakssApplyNextFlow(BakssApplyFlow bakssApplyFlow);

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
     * 删除申请流程
     * 
     * @param id 申请流程主键
     * @return 结果
     */
    public int deleteBakssApplyFlowById(Long id);

    /**
     * 批量删除申请流程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssApplyFlowByIds(Long[] ids);
}
