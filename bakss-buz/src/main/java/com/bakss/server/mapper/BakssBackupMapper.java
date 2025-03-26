package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssBackup;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author author
 * @date 2025-03-25
 */
public interface BakssBackupMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BakssBackup selectBakssBackupById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param bakssBackup 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BakssBackup> selectBakssBackupList(BakssBackup bakssBackup);

    /**
     * 新增【请填写功能名称】
     * 
     * @param bakssBackup 【请填写功能名称】
     * @return 结果
     */
    public int insertBakssBackup(BakssBackup bakssBackup);

    /**
     * 修改【请填写功能名称】
     * 
     * @param bakssBackup 【请填写功能名称】
     * @return 结果
     */
    public int updateBakssBackup(BakssBackup bakssBackup);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBakssBackupById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssBackupByIds(Long[] ids);
}
