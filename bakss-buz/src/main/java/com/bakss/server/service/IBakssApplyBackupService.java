package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssApplyBackup;

/**
 *  申请创建备份Service接口
 * 
 * @author author
 * @date 2025-05-06
 */
public interface IBakssApplyBackupService 
{
    /**
     * 查询 申请创建备份
     * 
     * @param id  申请创建备份主键
     * @return  申请创建备份
     */
    public BakssApplyBackup selectBakssApplyBackupById(Long id);

    /**
     * 查询 申请创建备份列表
     * 
     * @param bakssApplyBackup  申请创建备份
     * @return  申请创建备份集合
     */
    public List<BakssApplyBackup> selectBakssApplyBackupList(BakssApplyBackup bakssApplyBackup);

    /**
     * 新增 申请创建备份
     * 
     * @param bakssApplyBackup  申请创建备份
     * @return 结果
     */
    public int insertBakssApplyBackup(BakssApplyBackup bakssApplyBackup);

    /**
     * 修改 申请创建备份
     * 
     * @param bakssApplyBackup  申请创建备份
     * @return 结果
     */
    public int updateBakssApplyBackup(BakssApplyBackup bakssApplyBackup);

    /**
     * 批量删除 申请创建备份
     * 
     * @param ids 需要删除的 申请创建备份主键集合
     * @return 结果
     */
    public int deleteBakssApplyBackupByIds(Long[] ids);

    /**
     * 删除 申请创建备份信息
     * 
     * @param id  申请创建备份主键
     * @return 结果
     */
    public int deleteBakssApplyBackupById(Long id);
}
