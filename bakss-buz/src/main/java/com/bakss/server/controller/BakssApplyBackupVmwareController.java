package com.bakss.server.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.bakss.server.domain.backup.BakssApplyBackupVmware;
import com.bakss.server.service.IBakssApplyBackupVmwareService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * Apply Backup VMController
 *
 * @author author
 * @date 2025-05-10
 */
@RestController
@RequestMapping("/apply/backup/vmware")
public class BakssApplyBackupVmwareController extends BaseController
{
    @Resource
    private IBakssApplyBackupVmwareService bakssApplyBackupVmwareService;

    /**
     * 查询Apply Backup VM列表
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApplyBackupVmware bakssApplyBackupVmware)
    {
        startPage();
        List<BakssApplyBackupVmware> list = bakssApplyBackupVmwareService.selectBakssApplyBackupVmwareList(bakssApplyBackupVmware);
        return getDataTable(list);
    }

    /**
     * 导出Apply Backup VM列表
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:export')")
    @Log(title = "Apply Backup VM", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApplyBackupVmware bakssApplyBackupVmware)
    {
        List<BakssApplyBackupVmware> list = bakssApplyBackupVmwareService.selectBakssApplyBackupVmwareList(bakssApplyBackupVmware);
        ExcelUtil<BakssApplyBackupVmware> util = new ExcelUtil<BakssApplyBackupVmware>(BakssApplyBackupVmware.class);
        util.exportExcel(response, list, "Apply Backup VM数据");
    }

    /**
     * 获取Apply Backup VM详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssApplyBackupVmwareService.selectBakssApplyBackupVmwareById(id));
    }

    /**
     * 新增Apply Backup VM
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:add')")
    @Log(title = "Apply Backup VM", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApplyBackupVmware bakssApplyBackupVmware)
    {
        return toAjax(bakssApplyBackupVmwareService.insertBakssApplyBackupVmware(bakssApplyBackupVmware));
    }

    /**
     * 修改Apply Backup VM
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:edit')")
    @Log(title = "Apply Backup VM", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApplyBackupVmware bakssApplyBackupVmware)
    {
        return toAjax(bakssApplyBackupVmwareService.updateBakssApplyBackupVmware(bakssApplyBackupVmware));
    }

    /**
     * 删除Apply Backup VM
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:remove')")
    @Log(title = "Apply Backup VM", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssApplyBackupVmwareService.deleteBakssApplyBackupVmwareByIds(ids));
    }
}
