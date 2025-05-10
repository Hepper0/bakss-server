package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.backup.BakssApplyBackupVmware;

/**
 * Apply Backup VMService接口
 * 
 * @author author
 * @date 2025-05-10
 */
public interface IBakssApplyBackupVmwareService 
{
    /**
     * 查询Apply Backup VM
     * 
     * @param id Apply Backup VM主键
     * @return Apply Backup VM
     */
    public BakssApplyBackupVmware selectBakssApplyBackupVmwareById(Long id);

    public BakssApplyBackupVmware selectBakssApplyBackupVmwareByAppId(String appId);

    /**
     * 查询Apply Backup VM列表
     * 
     * @param bakssApplyBackupVmware Apply Backup VM
     * @return Apply Backup VM集合
     */
    public List<BakssApplyBackupVmware> selectBakssApplyBackupVmwareList(BakssApplyBackupVmware bakssApplyBackupVmware);

    /**
     * 新增Apply Backup VM
     * 
     * @param bakssApplyBackupVmware Apply Backup VM
     * @return 结果
     */
    public int insertBakssApplyBackupVmware(BakssApplyBackupVmware bakssApplyBackupVmware);

    /**
     * 修改Apply Backup VM
     * 
     * @param bakssApplyBackupVmware Apply Backup VM
     * @return 结果
     */
    public int updateBakssApplyBackupVmware(BakssApplyBackupVmware bakssApplyBackupVmware);

    /**
     * 批量删除Apply Backup VM
     * 
     * @param ids 需要删除的Apply Backup VM主键集合
     * @return 结果
     */
    public int deleteBakssApplyBackupVmwareByIds(Long[] ids);

    /**
     * 删除Apply Backup VM信息
     * 
     * @param id Apply Backup VM主键
     * @return 结果
     */
    public int deleteBakssApplyBackupVmwareById(Long id);
}
