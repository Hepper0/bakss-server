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
import com.bakss.server.domain.BakssApplyBackupPermis;
import com.bakss.server.service.IBakssApplyBackupPermisService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 备份权限申请Controller
 *
 * @author author
 * @date 2025-04-08
 */
@RestController
@RequestMapping("/service/permis")
public class BakssApplyBackupPermisController extends BaseController
{
    @Resource
    private IBakssApplyBackupPermisService bakssApplyBackupPermisService;

    /**
     * 查询备份权限申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:permis:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApplyBackupPermis bakssApplyBackupPermis)
    {
        startPage();
        List<BakssApplyBackupPermis> list = bakssApplyBackupPermisService.selectBakssApplyBackupPermisList(bakssApplyBackupPermis);
        return getDataTable(list);
    }

    /**
     * 导出备份权限申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:permis:export')")
    @Log(title = "备份权限申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApplyBackupPermis bakssApplyBackupPermis)
    {
        List<BakssApplyBackupPermis> list = bakssApplyBackupPermisService.selectBakssApplyBackupPermisList(bakssApplyBackupPermis);
        ExcelUtil<BakssApplyBackupPermis> util = new ExcelUtil<BakssApplyBackupPermis>(BakssApplyBackupPermis.class);
        util.exportExcel(response, list, "备份权限申请数据");
    }

    /**
     * 获取备份权限申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:permis:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssApplyBackupPermisService.selectBakssApplyBackupPermisById(id));
    }

    /**
     * 新增备份权限申请
     */
    @PreAuthorize("@ss.hasPermi('service:permis:add')")
    @Log(title = "备份权限申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApplyBackupPermis bakssApplyBackupPermis)
    {
        return toAjax(bakssApplyBackupPermisService.insertBakssApplyBackupPermis(bakssApplyBackupPermis));
    }

    /**
     * 修改备份权限申请
     */
    @PreAuthorize("@ss.hasPermi('service:permis:edit')")
    @Log(title = "备份权限申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApplyBackupPermis bakssApplyBackupPermis)
    {
        return toAjax(bakssApplyBackupPermisService.updateBakssApplyBackupPermis(bakssApplyBackupPermis));
    }

    /**
     * 删除备份权限申请
     */
    @PreAuthorize("@ss.hasPermi('service:permis:remove')")
    @Log(title = "备份权限申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssApplyBackupPermisService.deleteBakssApplyBackupPermisByIds(ids));
    }
}
