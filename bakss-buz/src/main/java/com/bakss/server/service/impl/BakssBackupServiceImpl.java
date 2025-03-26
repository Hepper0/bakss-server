package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssBackupMapper;
import com.bakss.server.domain.BakssBackup;
import com.bakss.server.service.IBakssBackupService;

import javax.annotation.Resource;

/**
 * 【请填写功能名称】Service业务层处理
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
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BakssBackup selectBakssBackupById(Long id)
    {
        return bakssBackupMapper.selectBakssBackupById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param bakssBackup 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BakssBackup> selectBakssBackupList(BakssBackup bakssBackup)
    {
        return bakssBackupMapper.selectBakssBackupList(bakssBackup);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param bakssBackup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBakssBackup(BakssBackup bakssBackup)
    {
        bakssBackup.setCreateTime(DateUtils.getNowDate());
        return bakssBackupMapper.insertBakssBackup(bakssBackup);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param bakssBackup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBakssBackup(BakssBackup bakssBackup)
    {
        bakssBackup.setUpdateTime(DateUtils.getNowDate());
        return bakssBackupMapper.updateBakssBackup(bakssBackup);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupByIds(Long[] ids)
    {
        return bakssBackupMapper.deleteBakssBackupByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupById(Long id)
    {
        return bakssBackupMapper.deleteBakssBackupById(id);
    }
}
