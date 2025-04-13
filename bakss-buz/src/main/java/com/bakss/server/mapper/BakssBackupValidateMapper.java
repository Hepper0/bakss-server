package com.bakss.server.mapper;

import java.util.List;
import com.bakss.server.domain.BakssBackupValidate;

/**
 * 备份授权有效期Mapper接口
 * 
 * @author author
 * @date 2025-04-14
 */
public interface BakssBackupValidateMapper 
{
    /**
     * 查询备份授权有效期
     * 
     * @param id 备份授权有效期主键
     * @return 备份授权有效期
     */
    public BakssBackupValidate selectBakssBackupValidateById(Long id);

    /**
     * 查询备份授权有效期列表
     * 
     * @param bakssBackupValidate 备份授权有效期
     * @return 备份授权有效期集合
     */
    public List<BakssBackupValidate> selectBakssBackupValidateList(BakssBackupValidate bakssBackupValidate);

    /**
     * 新增备份授权有效期
     * 
     * @param bakssBackupValidate 备份授权有效期
     * @return 结果
     */
    public int insertBakssBackupValidate(BakssBackupValidate bakssBackupValidate);

    /**
     * 修改备份授权有效期
     * 
     * @param bakssBackupValidate 备份授权有效期
     * @return 结果
     */
    public int updateBakssBackupValidate(BakssBackupValidate bakssBackupValidate);

    /**
     * 删除备份授权有效期
     * 
     * @param id 备份授权有效期主键
     * @return 结果
     */
    public int deleteBakssBackupValidateById(Long id);

    /**
     * 批量删除备份授权有效期
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBakssBackupValidateByIds(Long[] ids);
}
