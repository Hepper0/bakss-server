package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssAppFlow;

/**
 * 申请流程Mapper接口
 * 
 * @author author
 * @date 2025-04-02
 */
public interface BakssAppFlowMapper
{
    /**
     * 查询申请流程
     * 
     * @param id 申请流程主键
     * @return 申请流程
     */
    public BakssAppFlow selectBakssAppFlowById(Long id);

    public BakssAppFlow getBakssAppNextFlow(BakssAppFlow bakssAppFlow);

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
     * 删除申请流程
     * 
     * @param id 申请流程主键
     * @return 结果
     */
    public int deleteBakssAppFlowById(Long id);

    /**
     * 批量删除申请流程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssAppFlowByIds(Long[] ids);
}
