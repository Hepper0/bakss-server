package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyBackupMapper;
import com.bakss.server.domain.BakssApplyBackup;
import com.bakss.server.service.IBakssApplyBackupService;

import javax.annotation.Resource;

/**
 *  申请创建备份Service业务层处理
 *
 * @author author
 * @date 2025-05-06
 */
@Service
public class BakssApplyBackupServiceImpl implements IBakssApplyBackupService
{
    @Resource
    private BakssApplyBackupMapper bakssApplyBackupMapper;

    /**
     * 查询 申请创建备份
     *
     * @param id  申请创建备份主键
     * @return  申请创建备份
     */
    @Override
    public BakssApplyBackup selectBakssApplyBackupById(Long id)
    {
        return bakssApplyBackupMapper.selectBakssApplyBackupById(id);
    }

    @Override
    public BakssApplyBackup selectBakssApplyBackupByAppId(String appId)
    {
        return bakssApplyBackupMapper.selectBakssApplyBackupByAppId(appId);
    }

    /**
     * 查询 申请创建备份列表
     *
     * @param bakssApplyBackup  申请创建备份
     * @return  申请创建备份
     */
    @Override
    public List<BakssApplyBackup> selectBakssApplyBackupList(BakssApplyBackup bakssApplyBackup)
    {
        return bakssApplyBackupMapper.selectBakssApplyBackupList(bakssApplyBackup);
    }

    /**
     * 新增 申请创建备份
     *
     * @param bakssApplyBackup  申请创建备份
     * @return 结果
     */
    @Override
    public int insertBakssApplyBackup(BakssApplyBackup bakssApplyBackup)
    {
        bakssApplyBackup.setCreateTime(DateUtils.getNowDate());
        return bakssApplyBackupMapper.insertBakssApplyBackup(bakssApplyBackup);
    }

    /**
     * 修改 申请创建备份
     *
     * @param bakssApplyBackup  申请创建备份
     * @return 结果
     */
    @Override
    public int updateBakssApplyBackup(BakssApplyBackup bakssApplyBackup)
    {
        bakssApplyBackup.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyBackupMapper.updateBakssApplyBackup(bakssApplyBackup);
    }

    /**
     * 批量删除 申请创建备份
     *
     * @param ids 需要删除的 申请创建备份主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyBackupByIds(Long[] ids)
    {
        return bakssApplyBackupMapper.deleteBakssApplyBackupByIds(ids);
    }

    /**
     * 删除 申请创建备份信息
     *
     * @param id  申请创建备份主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyBackupById(Long id)
    {
        return bakssApplyBackupMapper.deleteBakssApplyBackupById(id);
    }
}
