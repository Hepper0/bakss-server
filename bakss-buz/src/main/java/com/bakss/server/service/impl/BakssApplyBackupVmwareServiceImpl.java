package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyBackupVmwareMapper;
import com.bakss.server.domain.backup.BakssApplyBackupVmware;
import com.bakss.server.service.IBakssApplyBackupVmwareService;

import javax.annotation.Resource;

/**
 * Apply Backup VMService业务层处理
 *
 * @author author
 * @date 2025-05-10
 */
@Service
public class BakssApplyBackupVmwareServiceImpl implements IBakssApplyBackupVmwareService
{
    @Resource
    private BakssApplyBackupVmwareMapper bakssApplyBackupVmwareMapper;

    /**
     * 查询Apply Backup VM
     *
     * @param id Apply Backup VM主键
     * @return Apply Backup VM
     */
    @Override
    public BakssApplyBackupVmware selectBakssApplyBackupVmwareById(Long id)
    {
        return bakssApplyBackupVmwareMapper.selectBakssApplyBackupVmwareById(id);
    }

    @Override
    public BakssApplyBackupVmware selectBakssApplyBackupVmwareByAppId(String appId)
    {
        return bakssApplyBackupVmwareMapper.selectBakssApplyBackupVmwareByAppId(appId);
    }

    /**
     * 查询Apply Backup VM列表
     *
     * @param bakssApplyBackupVmware Apply Backup VM
     * @return Apply Backup VM
     */
    @Override
    public List<BakssApplyBackupVmware> selectBakssApplyBackupVmwareList(BakssApplyBackupVmware bakssApplyBackupVmware)
    {
        return bakssApplyBackupVmwareMapper.selectBakssApplyBackupVmwareList(bakssApplyBackupVmware);
    }

    /**
     * 新增Apply Backup VM
     *
     * @param bakssApplyBackupVmware Apply Backup VM
     * @return 结果
     */
    @Override
    public int insertBakssApplyBackupVmware(BakssApplyBackupVmware bakssApplyBackupVmware)
    {
        bakssApplyBackupVmware.setCreateTime(DateUtils.getNowDate());
        return bakssApplyBackupVmwareMapper.insertBakssApplyBackupVmware(bakssApplyBackupVmware);
    }

    /**
     * 修改Apply Backup VM
     *
     * @param bakssApplyBackupVmware Apply Backup VM
     * @return 结果
     */
    @Override
    public int updateBakssApplyBackupVmware(BakssApplyBackupVmware bakssApplyBackupVmware)
    {
        bakssApplyBackupVmware.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyBackupVmwareMapper.updateBakssApplyBackupVmware(bakssApplyBackupVmware);
    }

    /**
     * 批量删除Apply Backup VM
     *
     * @param ids 需要删除的Apply Backup VM主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyBackupVmwareByIds(Long[] ids)
    {
        return bakssApplyBackupVmwareMapper.deleteBakssApplyBackupVmwareByIds(ids);
    }

    /**
     * 删除Apply Backup VM信息
     *
     * @param id Apply Backup VM主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyBackupVmwareById(Long id)
    {
        return bakssApplyBackupVmwareMapper.deleteBakssApplyBackupVmwareById(id);
    }
}
