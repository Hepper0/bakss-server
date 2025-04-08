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
import com.bakss.server.domain.BakssApply;
import com.bakss.server.service.IBakssApplyService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 申请Controller
 *
 * @author author
 * @date 2025-03-26
 */
@RestController
@RequestMapping("/service/apply")
public class BakssApplyController extends BaseController
{
    @Autowired
    private IBakssApplyService bakssApplyService;

    /**
     * 查询申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApply bakssApply)
    {
        startPage();
        List<BakssApply> list = bakssApplyService.selectBakssApplyList(bakssApply);
        return getDataTable(list);
    }

    /**
     * 导出申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:export')")
    @Log(title = "申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApply bakssApply)
    {
        List<BakssApply> list = bakssApplyService.selectBakssApplyList(bakssApply);
        ExcelUtil<BakssApply> util = new ExcelUtil<BakssApply>(BakssApply.class);
        util.exportExcel(response, list, "申请数据");
    }

    /**
     * 获取申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bakssApplyService.selectBakssApplyById(id));
    }

    /**
     * 新增申请
     */
    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApply bakssApply)
    {
        return toAjax(bakssApplyService.insertBakssApply(bakssApply));
    }

    /**
     * 修改申请
     */
    @PreAuthorize("@ss.hasPermi('service:apply:edit')")
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApply bakssApply)
    {
        return toAjax(bakssApplyService.updateBakssApply(bakssApply));
    }

    /**
     * 删除申请
     */
    @PreAuthorize("@ss.hasPermi('service:apply:remove')")
    @Log(title = "申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bakssApplyService.deleteBakssApplyByIds(ids));
    }

    /**
     * 审批申请
     */
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @PutMapping("/approve")
    public AjaxResult approve(@RequestBody BakssApply bakssApply)
    {
        bakssApplyService.approved(bakssApply);
        return success();
    }

    /**
     * 审批申请
     */
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    public AjaxResult reject(@RequestBody BakssApply bakssApply)
    {
        bakssApplyService.rejected(bakssApply);
        return success();
    }
}
