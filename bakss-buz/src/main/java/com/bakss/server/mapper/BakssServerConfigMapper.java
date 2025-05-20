package com.bakss.server.mapper;

import com.bakss.server.domain.BakssServerConfig;

import java.util.List;

/**
 * VeeamServerMapper接口
 * 
 * @author author
 * @date 2025-05-15
 */
public interface BakssServerConfigMapper 
{
    /**
     * 查询VeeamServer
     * 
     * @param id VeeamServer主键
     * @return VeeamServer
     */
    public BakssServerConfig selectBakssServerConfigById(Long id);

    /**
     * 查询VeeamServer列表
     * 
     * @param bakssServerConfig VeeamServer
     * @return VeeamServer集合
     */
    public List<BakssServerConfig> selectBakssServerConfigList(BakssServerConfig bakssServerConfig);

    /**
     * 新增VeeamServer
     * 
     * @param bakssServerConfig VeeamServer
     * @return 结果
     */
    public int insertBakssServerConfig(BakssServerConfig bakssServerConfig);

    /**
     * 修改VeeamServer
     * 
     * @param bakssServerConfig VeeamServer
     * @return 结果
     */
    public int updateBakssServerConfig(BakssServerConfig bakssServerConfig);

    /**
     * 删除VeeamServer
     * 
     * @param id VeeamServer主键
     * @return 结果
     */
    public int deleteBakssServerConfigById(Long id);

    /**
     * 批量删除VeeamServer
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssServerConfigByIds(Long[] ids);
}
