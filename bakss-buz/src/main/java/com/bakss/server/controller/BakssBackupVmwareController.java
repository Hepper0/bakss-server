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
import com.bakss.server.domain.BakssBackupVmware;
import com.bakss.server.service.IBakssBackupVmwareService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * VMController
 *
 * @author author
 * @date 2025-05-10
 */
@RestController
@RequestMapping("/system/vmware")
public class BakssBackupVmwareController extends BaseController
{
    @Resource
    private IBakssBackupVmwareService bakssBackupVmwareService;

    /**
     * 查询VM列表
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssBackupVmware bakssBackupVmware)
    {
        startPage();
        List<BakssBackupVmware> list = bakssBackupVmwareService.selectBakssBackupVmwareList(bakssBackupVmware);
        return getDataTable(list);
    }

    /**
     * 导出VM列表
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:export')")
    @Log(title = "VM", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssBackupVmware bakssBackupVmware)
    {
        List<BakssBackupVmware> list = bakssBackupVmwareService.selectBakssBackupVmwareList(bakssBackupVmware);
        ExcelUtil<BakssBackupVmware> util = new ExcelUtil<BakssBackupVmware>(BakssBackupVmware.class);
        util.exportExcel(response, list, "VM数据");
    }

    /**
     * 获取VM详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssBackupVmwareService.selectBakssBackupVmwareById(id));
    }

    /**
     * 新增VM
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:add')")
    @Log(title = "VM", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssBackupVmware bakssBackupVmware)
    {
        return toAjax(bakssBackupVmwareService.insertBakssBackupVmware(bakssBackupVmware));
    }

    /**
     * 修改VM
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:edit')")
    @Log(title = "VM", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssBackupVmware bakssBackupVmware)
    {
        return toAjax(bakssBackupVmwareService.updateBakssBackupVmware(bakssBackupVmware));
    }

    /**
     * 删除VM
     */
    @PreAuthorize("@ss.hasPermi('system:vmware:remove')")
    @Log(title = "VM", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssBackupVmwareService.deleteBakssBackupVmwareByIds(ids));
    }
}
