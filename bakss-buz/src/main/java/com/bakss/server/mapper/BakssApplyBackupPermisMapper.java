package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssApplyBackupPermis;

/**
 * 备份权限申请Mapper接口
 *
 * @author author
 * @date 2025-04-08
 */
public interface BakssApplyBackupPermisMapper
{
    /**
     * 查询备份权限申请
     *
     * @param id 备份权限申请主键
     * @return 备份权限申请
     */
    public BakssApplyBackupPermis selectBakssApplyBackupPermisById(Long id);

    public BakssApplyBackupPermis selectBakssApplyBackupPermisByAppId(String appId);

    /**
     * 查询备份权限申请列表
     *
     * @param bakssApplyBackupPermis 备份权限申请
     * @return 备份权限申请集合
     */
    public List<BakssApplyBackupPermis> selectBakssApplyBackupPermisList(BakssApplyBackupPermis bakssApplyBackupPermis);

    /**
     * 新增备份权限申请
     *
     * @param bakssApplyBackupPermis 备份权限申请
     * @return 结果
     */
    public int insertBakssApplyBackupPermis(BakssApplyBackupPermis bakssApplyBackupPermis);
    public int batchInsertApplyBackupPermis(List<BakssApplyBackupPermis> bakssApplyBackupPermis);

    /**
     * 修改备份权限申请
     *
     * @param bakssApplyBackupPermis 备份权限申请
     * @return 结果
     */
    public int updateBakssApplyBackupPermis(BakssApplyBackupPermis bakssApplyBackupPermis);

    /**
     * 删除备份权限申请
     *
     * @param id 备份权限申请主键
     * @return 结果
     */
    public int deleteBakssApplyBackupPermisById(Long id);

    /**
     * 批量删除备份权限申请
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssApplyBackupPermisByIds(Long[] ids);
}
