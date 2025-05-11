package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import com.bakss.common.utils.uuid.IdUtils;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssBackupMapper;
import com.bakss.server.domain.BakssBackup;
import com.bakss.server.service.IBakssBackupService;

import javax.annotation.Resource;

/**
 * 备份管理Service业务层处理
 *
 * @author author
 * @date 2025-03-25
 */
@Service
public class BakssBackupServiceImpl implements IBakssBackupService
{
    @Resource
    private BakssBackupMapper bakssBackupMapper;

    /**
     * 查询备份管理
     *
     * @param id 备份管理主键
     * @return 备份管理
     */
    @Override
    public BakssBackup selectBakssBackupById(Long id)
    {
        return bakssBackupMapper.selectBakssBackupById(id);
    }

    /**
     * 查询备份管理列表
     *
     * @param bakssBackup 备份管理
     * @return 备份管理
     */
    @Override
    public List<BakssBackup> selectBakssBackupList(BakssBackup bakssBackup)
    {
        return bakssBackupMapper.selectBakssBackupList(bakssBackup);
    }

    @Override
    public List<BakssBackup> selectBakssBackupByIds(Integer[] ids)
    {
        return bakssBackupMapper.selectBakssBackupByIds(ids);
    }

    public List<BakssBackup> getBackupList(String user) {
        return bakssBackupMapper.getBackupList(user);
    }

    public List<BakssBackup> getAllBackupList() {
        return bakssBackupMapper.getAllBackupList();
    }

    /**
     * 新增备份管理
     *
     * @param bakssBackup 备份管理
     * @return 结果
     */
    @Override
    public String insertBakssBackup(BakssBackup bakssBackup)
    {
        String backupId = IdUtils.simpleUUID();
        bakssBackup.setId(backupId);
        bakssBackup.setCreateTime(DateUtils.getNowDate());
        bakssBackupMapper.insertBakssBackup(bakssBackup);
        return backupId;
    }

    /**
     * 修改备份管理
     *
     * @param bakssBackup 备份管理
     * @return 结果
     */
    @Override
    public int updateBakssBackup(BakssBackup bakssBackup)
    {
        bakssBackup.setUpdateTime(DateUtils.getNowDate());
        return bakssBackupMapper.updateBakssBackup(bakssBackup);
    }

    /**
     * 批量删除备份管理
     *
     * @param ids 需要删除的备份管理主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupByIds(Long[] ids)
    {
        return bakssBackupMapper.deleteBakssBackupByIds(ids);
    }

    /**
     * 删除备份管理信息
     *
     * @param id 备份管理主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupById(Long id)
    {
        return bakssBackupMapper.deleteBakssBackupById(id);
    }
}
