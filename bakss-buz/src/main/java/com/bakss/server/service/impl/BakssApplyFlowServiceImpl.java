package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyFlowMapper;
import com.bakss.server.domain.BakssApplyFlow;
import com.bakss.server.service.IBakssApplyFlowService;

/**
 * 申请流程Service业务层处理
 *
 * @author author
 * @date 2025-04-02
 */
@Service
public class BakssApplyFlowServiceImpl implements IBakssApplyFlowService
{
    @Autowired
    private BakssApplyFlowMapper bakssApplyFlowMapper;

    /**
     * 查询申请流程
     *
     * @param id 申请流程主键
     * @return 申请流程
     */
    @Override
    public BakssApplyFlow selectBakssApplyFlowById(Long id)
    {
        return bakssApplyFlowMapper.selectBakssApplyFlowById(id);
    }

    /**
     * 查询申请流程列表
     *
     * @param bakssApplyFlow 申请流程
     * @return 申请流程
     */
    @Override
    public List<BakssApplyFlow> selectBakssApplyFlowList(BakssApplyFlow bakssApplyFlow)
    {
        return bakssApplyFlowMapper.selectBakssApplyFlowList(bakssApplyFlow);
    }

    /**
     * 新增申请流程
     *
     * @param bakssApplyFlow 申请流程
     * @return 结果
     */
    @Override
    public int insertBakssApplyFlow(BakssApplyFlow bakssApplyFlow)
    {
        bakssApplyFlow.setCreateTime(DateUtils.getNowDate());
        return bakssApplyFlowMapper.insertBakssApplyFlow(bakssApplyFlow);
    }

    /**
     * 修改申请流程
     *
     * @param bakssApplyFlow 申请流程
     * @return 结果
     */
    @Override
    public int updateBakssApplyFlow(BakssApplyFlow bakssApplyFlow)
    {
        bakssApplyFlow.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyFlowMapper.updateBakssApplyFlow(bakssApplyFlow);
    }

    /**
     * 批量删除申请流程
     *
     * @param ids 需要删除的申请流程主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyFlowByIds(Long[] ids)
    {
        return bakssApplyFlowMapper.deleteBakssApplyFlowByIds(ids);
    }

    /**
     * 删除申请流程信息
     *
     * @param id 申请流程主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyFlowById(Long id)
    {
        return bakssApplyFlowMapper.deleteBakssApplyFlowById(id);
    }
}
