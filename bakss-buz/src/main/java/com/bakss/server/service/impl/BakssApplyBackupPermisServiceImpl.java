package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyBackupPermisMapper;
import com.bakss.server.domain.BakssApplyBackupPermis;
import com.bakss.server.service.IBakssApplyBackupPermisService;

import javax.annotation.Resource;

/**
 * 备份权限申请Service业务层处理
 *
 * @author author
 * @date 2025-04-08
 */
@Service
public class BakssApplyBackupPermisServiceImpl implements IBakssApplyBackupPermisService
{
    @Resource
    private BakssApplyBackupPermisMapper bakssApplyBackupPermisMapper;

    /**
     * 查询备份权限申请
     *
     * @param id 备份权限申请主键
     * @return 备份权限申请
     */
    @Override
    public BakssApplyBackupPermis selectBakssApplyBackupPermisById(Long id)
    {
        return bakssApplyBackupPermisMapper.selectBakssApplyBackupPermisById(id);
    }

    @Override
    public BakssApplyBackupPermis selectBakssApplyBackupPermisByAppId(String appId)
    {
        return bakssApplyBackupPermisMapper.selectBakssApplyBackupPermisByAppId(appId);
    }

    /**
     * 查询备份权限申请列表
     *
     * @param bakssApplyBackupPermis 备份权限申请
     * @return 备份权限申请
     */
    @Override
    public List<BakssApplyBackupPermis> selectBakssApplyBackupPermisList(BakssApplyBackupPermis bakssApplyBackupPermis)
    {
        return bakssApplyBackupPermisMapper.selectBakssApplyBackupPermisList(bakssApplyBackupPermis);
    }

    /**
     * 新增备份权限申请
     *
     * @param bakssApplyBackupPermis 备份权限申请
     * @return 结果
     */
    @Override
    public int insertBakssApplyBackupPermis(BakssApplyBackupPermis bakssApplyBackupPermis)
    {
        bakssApplyBackupPermis.setCreateTime(DateUtils.getNowDate());
        return bakssApplyBackupPermisMapper.insertBakssApplyBackupPermis(bakssApplyBackupPermis);
    }

    @Override
    public void batchInsertApplyBackupPermis(List<BakssApplyBackupPermis> bakssApplyBackupPermis)
    {
        bakssApplyBackupPermis.forEach(bakssApplyBackupPermis1 -> bakssApplyBackupPermis1.setCreateTime(DateUtils.getNowDate()));
        bakssApplyBackupPermisMapper.batchInsertApplyBackupPermis(bakssApplyBackupPermis);
    }

    /**
     * 修改备份权限申请
     *
     * @param bakssApplyBackupPermis 备份权限申请
     * @return 结果
     */
    @Override
    public int updateBakssApplyBackupPermis(BakssApplyBackupPermis bakssApplyBackupPermis)
    {
        bakssApplyBackupPermis.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyBackupPermisMapper.updateBakssApplyBackupPermis(bakssApplyBackupPermis);
    }

    /**
     * 批量删除备份权限申请
     *
     * @param ids 需要删除的备份权限申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyBackupPermisByIds(Long[] ids)
    {
        return bakssApplyBackupPermisMapper.deleteBakssApplyBackupPermisByIds(ids);
    }

    /**
     * 删除备份权限申请信息
     *
     * @param id 备份权限申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyBackupPermisById(Long id)
    {
        return bakssApplyBackupPermisMapper.deleteBakssApplyBackupPermisById(id);
    }
}
