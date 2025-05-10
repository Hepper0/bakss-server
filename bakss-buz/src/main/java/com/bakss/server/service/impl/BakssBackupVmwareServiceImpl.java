package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssBackupVmwareMapper;
import com.bakss.server.domain.BakssBackupVmware;
import com.bakss.server.service.IBakssBackupVmwareService;

import javax.annotation.Resource;

/**
 * VMService业务层处理
 *
 * @author author
 * @date 2025-05-10
 */
@Service
public class BakssBackupVmwareServiceImpl implements IBakssBackupVmwareService
{
    @Resource
    private BakssBackupVmwareMapper bakssBackupVmwareMapper;

    /**
     * 查询VM
     *
     * @param id VM主键
     * @return VM
     */
    @Override
    public BakssBackupVmware selectBakssBackupVmwareById(Long id)
    {
        return bakssBackupVmwareMapper.selectBakssBackupVmwareById(id);
    }

    @Override
    public BakssBackupVmware selectBakssBackupVmwareByAppId(String appId)
    {
        return bakssBackupVmwareMapper.selectBakssBackupVmwareByAppId(appId);
    }

    /**
     * 查询VM列表
     *
     * @param bakssBackupVmware VM
     * @return VM
     */
    @Override
    public List<BakssBackupVmware> selectBakssBackupVmwareList(BakssBackupVmware bakssBackupVmware)
    {
        return bakssBackupVmwareMapper.selectBakssBackupVmwareList(bakssBackupVmware);
    }

    /**
     * 新增VM
     *
     * @param bakssBackupVmware VM
     * @return 结果
     */
    @Override
    public int insertBakssBackupVmware(BakssBackupVmware bakssBackupVmware)
    {
        bakssBackupVmware.setCreateTime(DateUtils.getNowDate());
        return bakssBackupVmwareMapper.insertBakssBackupVmware(bakssBackupVmware);
    }

    /**
     * 修改VM
     *
     * @param bakssBackupVmware VM
     * @return 结果
     */
    @Override
    public int updateBakssBackupVmware(BakssBackupVmware bakssBackupVmware)
    {
        bakssBackupVmware.setUpdateTime(DateUtils.getNowDate());
        return bakssBackupVmwareMapper.updateBakssBackupVmware(bakssBackupVmware);
    }

    /**
     * 批量删除VM
     *
     * @param ids 需要删除的VM主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupVmwareByIds(Long[] ids)
    {
        return bakssBackupVmwareMapper.deleteBakssBackupVmwareByIds(ids);
    }

    /**
     * 删除VM信息
     *
     * @param id VM主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupVmwareById(Long id)
    {
        return bakssBackupVmwareMapper.deleteBakssBackupVmwareById(id);
    }
}
