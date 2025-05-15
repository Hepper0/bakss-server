package com.bakss.server.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bakss.common.annotation.Log;
import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.common.enums.BusinessType;
import com.bakss.server.domain.BakssApplyBackup;
import com.bakss.server.service.IBakssApplyBackupService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 *  申请创建备份Controller
 *
 * @author author
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/application/createBackupApplication")
public class BakssApplyBackupController extends BaseController
{
    @Resource
    private IBakssApplyBackupService bakssApplyBackupService;

    /**
     * 查询 申请创建备份列表
     */
    @PreAuthorize("@ss.hasPermi('server:backup:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApplyBackup bakssApplyBackup)
    {
        startPage();
        List<BakssApplyBackup> list = bakssApplyBackupService.selectBakssApplyBackupList(bakssApplyBackup);
        return getDataTable(list);
    }

    /**
     * 导出 申请创建备份列表
     */
    @PreAuthorize("@ss.hasPermi('server:backup:export')")
    @Log(title = " 申请创建备份", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApplyBackup bakssApplyBackup)
    {
        List<BakssApplyBackup> list = bakssApplyBackupService.selectBakssApplyBackupList(bakssApplyBackup);
        ExcelUtil<BakssApplyBackup> util = new ExcelUtil<BakssApplyBackup>(BakssApplyBackup.class);
        util.exportExcel(response, list, " 申请创建备份数据");
    }

    /**
     * 获取 申请创建备份详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:backup:query')")
    @GetMapping(value = "/{appId}")
    public AjaxResult getInfo(@PathVariable("appId") String appId)
    {
        return success(bakssApplyBackupService.selectBakssApplyBackupByAppId(appId));
    }

    /**
     * 新增 申请创建备份
     */
    @PreAuthorize("@ss.hasPermi('server:backup:add')")
    @Log(title = " 申请创建备份", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApplyBackup bakssApplyBackup)
    {
        return toAjax(bakssApplyBackupService.insertBakssApplyBackup(bakssApplyBackup));
    }

    /**
     * 修改 申请创建备份
     */
    @PreAuthorize("@ss.hasPermi('server:backup:edit')")
    @Log(title = " 申请创建备份", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApplyBackup bakssApplyBackup)
    {
        return toAjax(bakssApplyBackupService.updateBakssApplyBackup(bakssApplyBackup));
    }

    /**
     * 删除 申请创建备份
     */
    @PreAuthorize("@ss.hasPermi('server:backup:remove')")
    @Log(title = " 申请创建备份", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssApplyBackupService.deleteBakssApplyBackupByIds(ids));
    }
}
