package com.bakss.server.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.SecurityUtils;
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
import com.bakss.server.domain.BakssBackup;
import com.bakss.server.service.IBakssBackupService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 备份Controller
 *
 * @author author
 * @date 2025-03-25
 */
@RestController
@RequestMapping("/service/backup")
public class BakssBackupController extends BaseController
{
    @Autowired
    private IBakssBackupService bakssBackupService;

    /**
     * 查询备份列表
     */
    @PreAuthorize("@ss.hasPermi('service:backup:query')")
    @GetMapping("")
    public TableDataInfo getListByIds(String[] ids)
    {
        startPage();
        List<BakssBackup> list = bakssBackupService.selectBakssBackupByIds(ids);
        return getDataTable(list);
    }

    /**
     * 查询备份列表
     */
    @PreAuthorize("@ss.hasPermi('service:backup:query')")
    @GetMapping("/list")
    public TableDataInfo list(BakssBackup bakssBackup)
    {
        startPage();
        List<BakssBackup> list = bakssBackupService.selectBakssBackupList(bakssBackup);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('service:backup:query')")
    @GetMapping("/my")
    public TableDataInfo myList(BakssBackup bakssBackup)
    {
        LoginUser user = SecurityUtils.getLoginUser();
        startPage();
        List<BakssBackup> list;
        if (SecurityUtils.isAdmin(user.getUserId())) {
            list = bakssBackupService.getAllBackupList();
        } else {
            list = bakssBackupService.getBackupList(user.getUsername());
        }

        return getDataTable(list);
    }

    /**
     * 导出备份列表
     */
    @PreAuthorize("@ss.hasPermi('service:backup:export')")
    @Log(title = "备份", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssBackup bakssBackup)
    {
        List<BakssBackup> list = bakssBackupService.selectBakssBackupList(bakssBackup);
        ExcelUtil<BakssBackup> util = new ExcelUtil<BakssBackup>(BakssBackup.class);
        util.exportExcel(response, list, "备份数据");
    }

    /**
     * 获取备份详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:backup:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bakssBackupService.selectBakssBackupById(id));
    }

    /**
     * 新增备份
     */
    @PreAuthorize("@ss.hasPermi('service:backup:add')")
    @Log(title = "备份", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssBackup bakssBackup)
    {
        bakssBackupService.insertBakssBackup(bakssBackup);
        return success();
    }

    /**
     * 修改备份
     */
    @PreAuthorize("@ss.hasPermi('service:backup:edit')")
    @Log(title = "备份", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssBackup bakssBackup)
    {
        return toAjax(bakssBackupService.updateBakssBackup(bakssBackup));
    }

    /**
     * 删除备份
     */
    @PreAuthorize("@ss.hasPermi('service:backup:remove')")
    @Log(title = "备份", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssBackupService.deleteBakssBackupByIds(ids));
    }
}
