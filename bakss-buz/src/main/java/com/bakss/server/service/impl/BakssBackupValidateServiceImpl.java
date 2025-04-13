package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssBackupValidateMapper;
import com.bakss.server.domain.BakssBackupValidate;
import com.bakss.server.service.IBakssBackupValidateService;

import javax.annotation.Resource;

/**
 * 备份授权有效期Service业务层处理
 *
 * @author author
 * @date 2025-04-14
 */
@Service
public class BakssBackupValidateServiceImpl implements IBakssBackupValidateService
{
    @Resource
    private BakssBackupValidateMapper bakssBackupValidateMapper;

    /**
     * 查询备份授权有效期
     *
     * @param id 备份授权有效期主键
     * @return 备份授权有效期
     */
    @Override
    public BakssBackupValidate selectBakssBackupValidateById(Long id)
    {
        return bakssBackupValidateMapper.selectBakssBackupValidateById(id);
    }

    /**
     * 查询备份授权有效期列表
     *
     * @param bakssBackupValidate 备份授权有效期
     * @return 备份授权有效期
     */
    @Override
    public List<BakssBackupValidate> selectBakssBackupValidateList(BakssBackupValidate bakssBackupValidate)
    {
        return bakssBackupValidateMapper.selectBakssBackupValidateList(bakssBackupValidate);
    }

    /**
     * 新增备份授权有效期
     *
     * @param bakssBackupValidate 备份授权有效期
     * @return 结果
     */
    @Override
    public int insertBakssBackupValidate(BakssBackupValidate bakssBackupValidate)
    {
        bakssBackupValidate.setCreateTime(DateUtils.getNowDate());
        return bakssBackupValidateMapper.insertBakssBackupValidate(bakssBackupValidate);
    }

    /**
     * 修改备份授权有效期
     *
     * @param bakssBackupValidate 备份授权有效期
     * @return 结果
     */
    @Override
    public int updateBakssBackupValidate(BakssBackupValidate bakssBackupValidate)
    {
        bakssBackupValidate.setUpdateTime(DateUtils.getNowDate());
        return bakssBackupValidateMapper.updateBakssBackupValidate(bakssBackupValidate);
    }

    /**
     * 批量删除备份授权有效期
     *
     * @param ids 需要删除的备份授权有效期主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupValidateByIds(Long[] ids)
    {
        return bakssBackupValidateMapper.deleteBakssBackupValidateByIds(ids);
    }

    /**
     * 删除备份授权有效期信息
     *
     * @param id 备份授权有效期主键
     * @return 结果
     */
    @Override
    public int deleteBakssBackupValidateById(Long id)
    {
        return bakssBackupValidateMapper.deleteBakssBackupValidateById(id);
    }
}
