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
import com.bakss.server.domain.BakssBackupValidate;
import com.bakss.server.service.IBakssBackupValidateService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 备份授权有效期Controller
 *
 * @author author
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/service/validate")
public class BakssBackupValidateController extends BaseController
{
    @Resource
    private IBakssBackupValidateService bakssBackupValidateService;

    /**
     * 查询备份授权有效期列表
     */
    @PreAuthorize("@ss.hasPermi('server:validate:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssBackupValidate bakssBackupValidate)
    {
        startPage();
        List<BakssBackupValidate> list = bakssBackupValidateService.selectBakssBackupValidateList(bakssBackupValidate);
        return getDataTable(list);
    }

    /**
     * 导出备份授权有效期列表
     */
    @PreAuthorize("@ss.hasPermi('server:validate:export')")
    @Log(title = "备份授权有效期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssBackupValidate bakssBackupValidate)
    {
        List<BakssBackupValidate> list = bakssBackupValidateService.selectBakssBackupValidateList(bakssBackupValidate);
        ExcelUtil<BakssBackupValidate> util = new ExcelUtil<BakssBackupValidate>(BakssBackupValidate.class);
        util.exportExcel(response, list, "备份授权有效期数据");
    }

    /**
     * 获取备份授权有效期详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:validate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssBackupValidateService.selectBakssBackupValidateById(id));
    }

    /**
     * 新增备份授权有效期
     */
    @PreAuthorize("@ss.hasPermi('server:validate:add')")
    @Log(title = "备份授权有效期", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssBackupValidate bakssBackupValidate)
    {
        return toAjax(bakssBackupValidateService.insertBakssBackupValidate(bakssBackupValidate));
    }

    /**
     * 修改备份授权有效期
     */
    @PreAuthorize("@ss.hasPermi('server:validate:edit')")
    @Log(title = "备份授权有效期", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssBackupValidate bakssBackupValidate)
    {
        return toAjax(bakssBackupValidateService.updateBakssBackupValidate(bakssBackupValidate));
    }

    /**
     * 删除备份授权有效期
     */
    @PreAuthorize("@ss.hasPermi('server:validate:remove')")
    @Log(title = "备份授权有效期", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssBackupValidateService.deleteBakssBackupValidateByIds(ids));
    }
}
