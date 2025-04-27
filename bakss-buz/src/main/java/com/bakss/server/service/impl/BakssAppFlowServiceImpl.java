package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssAppFlowMapper;
import com.bakss.server.domain.BakssAppFlow;
import com.bakss.server.service.IBakssAppFlowService;

import javax.annotation.Resource;

/**
 * 申请流程Service业务层处理
 *
 * @author author
 * @date 2025-04-02
 */
@Service
public class BakssAppFlowServiceImpl implements IBakssAppFlowService
{
    @Resource
    private BakssAppFlowMapper bakssAppFlowMapper;

    /**
     * 查询申请流程
     *
     * @param id 申请流程主键
     * @return 申请流程
     */
    @Override
    public BakssAppFlow selectBakssAppFlowById(Long id)
    {
        return bakssAppFlowMapper.selectBakssAppFlowById(id);
    }

    /**
     * 查询申请流程列表
     *
     * @param bakssAppFlow 申请流程
     * @return 申请流程
     */
    @Override
    public List<BakssAppFlow> selectBakssAppFlowList(BakssAppFlow bakssAppFlow)
    {
        return bakssAppFlowMapper.selectBakssAppFlowList(bakssAppFlow);
    }

    /**
     * 新增申请流程
     *
     * @param bakssAppFlow 申请流程
     * @return 结果
     */
    @Override
    public int insertBakssAppFlow(BakssAppFlow bakssAppFlow)
    {
        bakssAppFlow.setCreateTime(DateUtils.getNowDate());
        return bakssAppFlowMapper.insertBakssAppFlow(bakssAppFlow);
    }

    /**
     * 修改申请流程
     *
     * @param bakssAppFlow 申请流程
     * @return 结果
     */
    @Override
    public int updateBakssAppFlow(BakssAppFlow bakssAppFlow)
    {
        bakssAppFlow.setUpdateTime(DateUtils.getNowDate());
        return bakssAppFlowMapper.updateBakssAppFlow(bakssAppFlow);
    }

    /**
     * 批量删除申请流程
     *
     * @param ids 需要删除的申请流程主键
     * @return 结果
     */
    @Override
    public int deleteBakssAppFlowByIds(Long[] ids)
    {
        return bakssAppFlowMapper.deleteBakssAppFlowByIds(ids);
    }

    /**
     * 删除申请流程信息
     *
     * @param id 申请流程主键
     * @return 结果
     */
    @Override
    public int deleteBakssAppFlowById(Long id)
    {
        return bakssAppFlowMapper.deleteBakssAppFlowById(id);
    }
}
