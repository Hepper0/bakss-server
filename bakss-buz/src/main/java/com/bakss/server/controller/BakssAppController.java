package com.bakss.server.controller;

import java.util.List;
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
import com.bakss.server.domain.BakssApp;
import com.bakss.server.service.IBakssAppService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 申请Controller
 *
 * @author author
 * @date 2025-03-26
 */
@RestController
@RequestMapping("/service/application")
public class BakssAppController extends BaseController
{
    @Autowired
    private IBakssAppService bakssAppService;

    /**
     * 查询申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApp bakssApp)
    {
        startPage();
        List<BakssApp> list = bakssAppService.selectBakssAppList(bakssApp);
        return getDataTable(list);
    }

    /**
     * 导出申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:export')")
    @Log(title = "申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApp bakssApp)
    {
        List<BakssApp> list = bakssAppService.selectBakssAppList(bakssApp);
        ExcelUtil<BakssApp> util = new ExcelUtil<BakssApp>(BakssApp.class);
        util.exportExcel(response, list, "申请数据");
    }

    /**
     * 获取申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bakssAppService.selectBakssAppById(id));
    }

    /**
     * 新增申请
     */
    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApp bakssApp)
    {
        return toAjax(bakssAppService.insertBakssApp(bakssApp));
    }

    /**
     * 修改申请
     */
    @PreAuthorize("@ss.hasPermi('service:apply:edit')")
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApp bakssApp)
    {
        return toAjax(bakssAppService.updateBakssApp(bakssApp));
    }

    /**
     * 删除申请
     */
    @PreAuthorize("@ss.hasPermi('service:apply:remove')")
    @Log(title = "申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bakssAppService.deleteBakssAppByIds(ids));
    }

    /**
     * 审批申请
     */
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @PutMapping("/approve")
    public AjaxResult approve(@RequestBody BakssApp bakssApp)
    {
        bakssAppService.approved(bakssApp);
        return success();
    }

    /**
     * 审批申请
     */
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    public AjaxResult reject(@RequestBody BakssApp bakssApp)
    {
        bakssAppService.rejected(bakssApp);
        return success();
    }
}
