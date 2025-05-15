package com.bakss.veeam.service;

import java.util.List;
import com.bakss.veeam.domain.BakssServerConfig;

/**
 * VeeamServerService接口
 * 
 * @author author
 * @date 2025-05-15
 */
public interface IBakssServerConfigService 
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
     * 批量删除VeeamServer
     * 
     * @param ids 需要删除的VeeamServer主键集合
     * @return 结果
     */
    public int deleteBakssServerConfigByIds(Long[] ids);

    /**
     * 删除VeeamServer信息
     * 
     * @param id VeeamServer主键
     * @return 结果
     */
    public int deleteBakssServerConfigById(Long id);
}
