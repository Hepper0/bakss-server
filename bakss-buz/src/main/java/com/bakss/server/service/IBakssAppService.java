package com.bakss.server.service;

import java.util.List;
import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.BakssTask;

/**
 * 申请Service接口
 * 
 * @author author
 * @date 2025-03-26
 */
public interface IBakssAppService
{
    /**
     * 查询申请
     * 
     * @param id 申请主键
     * @return 申请
     */
    public BakssApp selectBakssAppById(String id);

    /**
     * 查询申请列表
     * 
     * @param bakssApp 申请
     * @return 申请集合
     */
    public List<BakssApp> selectBakssAppList(BakssApp bakssApp);

    /**
     * 新增申请
     * 
     * @param bakssApp 申请
     * @return 结果
     */
    public int insertBakssApp(BakssApp bakssApp);

    /**
     * 修改申请
     * 
     * @param bakssApp 申请
     * @return 结果
     */
    public int updateBakssApp(BakssApp bakssApp);

    /**
     * 批量删除申请
     * 
     * @param ids 需要删除的申请主键集合
     * @return 结果
     */
    public int deleteBakssAppByIds(String[] ids);

    /**
     * 删除申请信息
     * 
     * @param id 申请主键
     * @return 结果
     */
    public int deleteBakssAppById(String id);

    public void approved(BakssApp App);

    public void rejected(BakssApp App);

}
