package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssAppFlow;

/**
 * 申请流程Service接口
 * 
 * @author author
 * @date 2025-04-02
 */
public interface IBakssAppFlowService
{
    /**
     * 查询申请流程
     * 
     * @param id 申请流程主键
     * @return 申请流程
     */
    public BakssAppFlow selectBakssAppFlowById(Long id);

    /**
     * 查询申请流程列表
     * 
     * @param bakssAppFlow 申请流程
     * @return 申请流程集合
     */
    public List<BakssAppFlow> selectBakssAppFlowList(BakssAppFlow bakssAppFlow);

    /**
     * 新增申请流程
     * 
     * @param bakssAppFlow 申请流程
     * @return 结果
     */
    public int insertBakssAppFlow(BakssAppFlow bakssAppFlow);

    /**
     * 修改申请流程
     * 
     * @param bakssAppFlow 申请流程
     * @return 结果
     */
    public int updateBakssAppFlow(BakssAppFlow bakssAppFlow);

    /**
     * 批量删除申请流程
     * 
     * @param ids 需要删除的申请流程主键集合
     * @return 结果
     */
    public int deleteBakssAppFlowByIds(Long[] ids);

    /**
     * 删除申请流程信息
     * 
     * @param id 申请流程主键
     * @return 结果
     */
    public int deleteBakssAppFlowById(Long id);
}
