package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssBackup;

/**
 * 备份Service接口
 * 
 * @author author
 * @date 2025-03-25
 */
public interface IBakssBackupService 
{
    /**
     * 查询备份
     * 
     * @param id 备份主键
     * @return 备份
     */
    public BakssBackup selectBakssBackupById(Long id);

    /**
     * 查询备份列表
     * 
     * @param bakssBackup 备份
     * @return 备份集合
     */
    public List<BakssBackup> selectBakssBackupList(BakssBackup bakssBackup);

    /**
     * 新增备份
     * 
     * @param bakssBackup 备份
     * @return 结果
     */
    public int insertBakssBackup(BakssBackup bakssBackup);

    /**
     * 修改备份
     * 
     * @param bakssBackup 备份
     * @return 结果
     */
    public int updateBakssBackup(BakssBackup bakssBackup);

    /**
     * 批量删除备份
     * 
     * @param ids 需要删除的备份主键集合
     * @return 结果
     */
    public int deleteBakssBackupByIds(Long[] ids);

    /**
     * 删除备份信息
     * 
     * @param id 备份主键
     * @return 结果
     */
    public int deleteBakssBackupById(Long id);
}
