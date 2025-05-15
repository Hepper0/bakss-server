package com.bakss.veeam.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.veeam.mapper.BakssServerConfigMapper;
import com.bakss.veeam.domain.BakssServerConfig;
import com.bakss.veeam.service.IBakssServerConfigService;

import javax.annotation.Resource;

/**
 * VeeamServerService业务层处理
 *
 * @author author
 * @date 2025-05-15
 */
@Service
public class BakssServerConfigServiceImpl implements IBakssServerConfigService
{
    @Resource
    private BakssServerConfigMapper bakssServerConfigMapper;

    /**
     * 查询VeeamServer
     *
     * @param id VeeamServer主键
     * @return VeeamServer
     */
    @Override
    public BakssServerConfig selectBakssServerConfigById(Long id)
    {
        return bakssServerConfigMapper.selectBakssServerConfigById(id);
    }

    /**
     * 查询VeeamServer列表
     *
     * @param bakssServerConfig VeeamServer
     * @return VeeamServer
     */
    @Override
    public List<BakssServerConfig> selectBakssServerConfigList(BakssServerConfig bakssServerConfig)
    {
        return bakssServerConfigMapper.selectBakssServerConfigList(bakssServerConfig);
    }

    /**
     * 新增VeeamServer
     *
     * @param bakssServerConfig VeeamServer
     * @return 结果
     */
    @Override
    public int insertBakssServerConfig(BakssServerConfig bakssServerConfig)
    {
        bakssServerConfig.setCreateTime(DateUtils.getNowDate());
        return bakssServerConfigMapper.insertBakssServerConfig(bakssServerConfig);
    }

    /**
     * 修改VeeamServer
     *
     * @param bakssServerConfig VeeamServer
     * @return 结果
     */
    @Override
    public int updateBakssServerConfig(BakssServerConfig bakssServerConfig)
    {
        bakssServerConfig.setUpdateTime(DateUtils.getNowDate());
        return bakssServerConfigMapper.updateBakssServerConfig(bakssServerConfig);
    }

    /**
     * 批量删除VeeamServer
     *
     * @param ids 需要删除的VeeamServer主键
     * @return 结果
     */
    @Override
    public int deleteBakssServerConfigByIds(Long[] ids)
    {
        return bakssServerConfigMapper.deleteBakssServerConfigByIds(ids);
    }

    /**
     * 删除VeeamServer信息
     *
     * @param id VeeamServer主键
     * @return 结果
     */
    @Override
    public int deleteBakssServerConfigById(Long id)
    {
        return bakssServerConfigMapper.deleteBakssServerConfigById(id);
    }
}
