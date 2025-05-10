package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssBackupVmware;

/**
 * VMService接口
 * 
 * @author author
 * @date 2025-05-10
 */
public interface IBakssBackupVmwareService 
{
    /**
     * 查询VM
     * 
     * @param id VM主键
     * @return VM
     */
    public BakssBackupVmware selectBakssBackupVmwareById(Long id);

    public BakssBackupVmware selectBakssBackupVmwareByAppId(String appId);

    /**
     * 查询VM列表
     * 
     * @param bakssBackupVmware VM
     * @return VM集合
     */
    public List<BakssBackupVmware> selectBakssBackupVmwareList(BakssBackupVmware bakssBackupVmware);

    /**
     * 新增VM
     * 
     * @param bakssBackupVmware VM
     * @return 结果
     */
    public int insertBakssBackupVmware(BakssBackupVmware bakssBackupVmware);

    /**
     * 修改VM
     * 
     * @param bakssBackupVmware VM
     * @return 结果
     */
    public int updateBakssBackupVmware(BakssBackupVmware bakssBackupVmware);

    /**
     * 批量删除VM
     * 
     * @param ids 需要删除的VM主键集合
     * @return 结果
     */
    public int deleteBakssBackupVmwareByIds(Long[] ids);

    /**
     * 删除VM信息
     * 
     * @param id VM主键
     * @return 结果
     */
    public int deleteBakssBackupVmwareById(Long id);
}
