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
import com.bakss.server.domain.BakssAppStep;
import com.bakss.server.service.IBakssAppStepService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 申请步骤Controller
 *
 * @author author
 * @date 2025-04-06
 */
@RestController
@RequestMapping("/service/step")
public class BakssAppStepController extends BaseController
{
    @Resource
    private IBakssAppStepService bakssAppStepService;

    /**
     * 查询申请步骤列表
     */
    @PreAuthorize("@ss.hasPermi('service:step:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssAppStep bakssAppStep)
    {
        startPage();
        List<BakssAppStep> list = bakssAppStepService.selectBakssAppStepList(bakssAppStep);
        return getDataTable(list);
    }

    /**
     * 导出申请步骤列表
     */
    @PreAuthorize("@ss.hasPermi('service:step:export')")
    @Log(title = "申请步骤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssAppStep bakssAppStep)
    {
        List<BakssAppStep> list = bakssAppStepService.selectBakssAppStepList(bakssAppStep);
        ExcelUtil<BakssAppStep> util = new ExcelUtil<BakssAppStep>(BakssAppStep.class);
        util.exportExcel(response, list, "申请步骤数据");
    }

    /**
     * 获取申请步骤详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:step:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssAppStepService.selectBakssAppStepById(id));
    }

    /**
     * 新增申请步骤
     */
    @PreAuthorize("@ss.hasPermi('service:step:add')")
    @Log(title = "申请步骤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssAppStep bakssAppStep)
    {
        return toAjax(bakssAppStepService.insertBakssAppStep(bakssAppStep));
    }

    /**
     * 修改申请步骤
     */
    @PreAuthorize("@ss.hasPermi('service:step:edit')")
    @Log(title = "申请步骤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssAppStep bakssAppStep)
    {
        return toAjax(bakssAppStepService.updateBakssAppStep(bakssAppStep));
    }

    /**
     * 删除申请步骤
     */
    @PreAuthorize("@ss.hasPermi('service:step:remove')")
    @Log(title = "申请步骤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssAppStepService.deleteBakssAppStepByIds(ids));
    }
}
