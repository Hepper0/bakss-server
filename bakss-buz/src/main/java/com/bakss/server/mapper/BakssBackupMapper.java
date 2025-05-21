package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssBackup;
import org.apache.ibatis.annotations.Param;

/**
 * 备份管理Mapper接口
 * 
 * @author author
 * @date 2025-03-25
 */
public interface BakssBackupMapper 
{
    /**
     * 查询备份管理
     * 
     * @param id 备份管理主键
     * @return 备份管理
     */
    public BakssBackup selectBakssBackupById(String id);

    public BakssBackup selectBakssBackupByName(String name);

    /**
     * 查询备份管理列表
     * 
     * @return 备份管理集合
     */
    public List<BakssBackup> selectBakssBackupByIds(String[] ids);

    public List<BakssBackup> selectBakssBackupList(BakssBackup bakssBackup);

    public List<BakssBackup> getBackupList(@Param("user") String user);

    public List<BakssBackup> getAllBackupList();

    /**
     * 新增备份管理
     * 
     * @param bakssBackup 备份管理
     * @return 结果
     */
    public int insertBakssBackup(BakssBackup bakssBackup);

    /**
     * 修改备份管理
     * 
     * @param bakssBackup 备份管理
     * @return 结果
     */
    public int updateBakssBackup(BakssBackup bakssBackup);

    /**
     * 删除备份管理
     * 
     * @param id 备份管理主键
     * @return 结果
     */
    public int deleteBakssBackupById(Long id);

    /**
     * 批量删除备份管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssBackupByIds(Long[] ids);
}
