package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssApplyStrategy;

/**
 * 备份策略申请Mapper接口
 * 
 * @author author
 * @date 2025-04-08
 */
public interface BakssApplyStrategyMapper 
{
    /**
     * 查询备份策略申请
     * 
     * @param id 备份策略申请主键
     * @return 备份策略申请
     */
    public BakssApplyStrategy selectBakssApplyStrategyById(Long id);

    public BakssApplyStrategy selectBakssApplyStrategyByAppId(String appId);

    /**
     * 查询备份策略申请列表
     * 
     * @param bakssApplyStrategy 备份策略申请
     * @return 备份策略申请集合
     */
    public List<BakssApplyStrategy> selectBakssApplyStrategyList(BakssApplyStrategy bakssApplyStrategy);

    /**
     * 新增备份策略申请
     * 
     * @param bakssApplyStrategy 备份策略申请
     * @return 结果
     */
    public int insertBakssApplyStrategy(BakssApplyStrategy bakssApplyStrategy);

    /**
     * 修改备份策略申请
     * 
     * @param bakssApplyStrategy 备份策略申请
     * @return 结果
     */
    public int updateBakssApplyStrategy(BakssApplyStrategy bakssApplyStrategy);

    /**
     * 删除备份策略申请
     * 
     * @param id 备份策略申请主键
     * @return 结果
     */
    public int deleteBakssApplyStrategyById(Long id);

    /**
     * 批量删除备份策略申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssApplyStrategyByIds(Long[] ids);
}
