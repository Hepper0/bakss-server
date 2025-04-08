package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyStrategyMapper;
import com.bakss.server.domain.BakssApplyStrategy;
import com.bakss.server.service.IBakssApplyStrategyService;

/**
 * 备份策略申请Service业务层处理
 *
 * @author author
 * @date 2025-04-08
 */
@Service
public class BakssApplyStrategyServiceImpl implements IBakssApplyStrategyService
{
    @Autowired
    private BakssApplyStrategyMapper bakssApplyStrategyMapper;

    /**
     * 查询备份策略申请
     *
     * @param id 备份策略申请主键
     * @return 备份策略申请
     */
    @Override
    public BakssApplyStrategy selectBakssApplyStrategyById(Long id)
    {
        return bakssApplyStrategyMapper.selectBakssApplyStrategyById(id);
    }

    /**
     * 查询备份策略申请列表
     *
     * @param bakssApplyStrategy 备份策略申请
     * @return 备份策略申请
     */
    @Override
    public List<BakssApplyStrategy> selectBakssApplyStrategyList(BakssApplyStrategy bakssApplyStrategy)
    {
        return bakssApplyStrategyMapper.selectBakssApplyStrategyList(bakssApplyStrategy);
    }

    /**
     * 新增备份策略申请
     *
     * @param bakssApplyStrategy 备份策略申请
     * @return 结果
     */
    @Override
    public int insertBakssApplyStrategy(BakssApplyStrategy bakssApplyStrategy)
    {
        bakssApplyStrategy.setCreateTime(DateUtils.getNowDate());
        return bakssApplyStrategyMapper.insertBakssApplyStrategy(bakssApplyStrategy);
    }

    /**
     * 修改备份策略申请
     *
     * @param bakssApplyStrategy 备份策略申请
     * @return 结果
     */
    @Override
    public int updateBakssApplyStrategy(BakssApplyStrategy bakssApplyStrategy)
    {
        bakssApplyStrategy.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyStrategyMapper.updateBakssApplyStrategy(bakssApplyStrategy);
    }

    /**
     * 批量删除备份策略申请
     *
     * @param ids 需要删除的备份策略申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyStrategyByIds(Long[] ids)
    {
        return bakssApplyStrategyMapper.deleteBakssApplyStrategyByIds(ids);
    }

    /**
     * 删除备份策略申请信息
     *
     * @param id 备份策略申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyStrategyById(Long id)
    {
        return bakssApplyStrategyMapper.deleteBakssApplyStrategyById(id);
    }
}
