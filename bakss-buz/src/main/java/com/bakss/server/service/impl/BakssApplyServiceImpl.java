package com.bakss.server.service.impl;

import java.util.List;
import com.bakss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakss.server.mapper.BakssApplyMapper;
import com.bakss.server.domain.BakssApply;
import com.bakss.server.service.IBakssApplyService;

import javax.annotation.Resource;

/**
 * 申请Service业务层处理
 *
 * @author author
 * @date 2025-03-26
 */
@Service
public class BakssApplyServiceImpl implements IBakssApplyService
{
    @Resource
    private BakssApplyMapper bakssApplyMapper;

    /**
     * 查询申请
     *
     * @param id 申请主键
     * @return 申请
     */
    @Override
    public BakssApply selectBakssApplyById(String id)
    {
        return bakssApplyMapper.selectBakssApplyById(id);
    }

    /**
     * 查询申请列表
     *
     * @param bakssApply 申请
     * @return 申请
     */
    @Override
    public List<BakssApply> selectBakssApplyList(BakssApply bakssApply)
    {
        return bakssApplyMapper.selectBakssApplyList(bakssApply);
    }

    /**
     * 新增申请
     *
     * @param bakssApply 申请
     * @return 结果
     */
    @Override
    public int insertBakssApply(BakssApply bakssApply)
    {
        bakssApply.setCreateTime(DateUtils.getNowDate());
        return bakssApplyMapper.insertBakssApply(bakssApply);
    }

    /**
     * 修改申请
     *
     * @param bakssApply 申请
     * @return 结果
     */
    @Override
    public int updateBakssApply(BakssApply bakssApply)
    {
        bakssApply.setUpdateTime(DateUtils.getNowDate());
        return bakssApplyMapper.updateBakssApply(bakssApply);
    }

    /**
     * 批量删除申请
     *
     * @param ids 需要删除的申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyByIds(String[] ids)
    {
        return bakssApplyMapper.deleteBakssApplyByIds(ids);
    }

    /**
     * 删除申请信息
     *
     * @param id 申请主键
     * @return 结果
     */
    @Override
    public int deleteBakssApplyById(String id)
    {
        return bakssApplyMapper.deleteBakssApplyById(id);
    }
}
