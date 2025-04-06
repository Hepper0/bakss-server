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
import com.bakss.server.domain.BakssApplyStep;
import com.bakss.server.service.IBakssApplyStepService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author author
 * @date 2025-04-06
 */
@RestController
@RequestMapping("/system/step")
public class BakssApplyStepController extends BaseController
{
    @Resource
    private IBakssApplyStepService bakssApplyStepService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:step:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApplyStep bakssApplyStep)
    {
        startPage();
        List<BakssApplyStep> list = bakssApplyStepService.selectBakssApplyStepList(bakssApplyStep);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:step:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApplyStep bakssApplyStep)
    {
        List<BakssApplyStep> list = bakssApplyStepService.selectBakssApplyStepList(bakssApplyStep);
        ExcelUtil<BakssApplyStep> util = new ExcelUtil<BakssApplyStep>(BakssApplyStep.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:step:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssApplyStepService.selectBakssApplyStepById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:step:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApplyStep bakssApplyStep)
    {
        return toAjax(bakssApplyStepService.insertBakssApplyStep(bakssApplyStep));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:step:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApplyStep bakssApplyStep)
    {
        return toAjax(bakssApplyStepService.updateBakssApplyStep(bakssApplyStep));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:step:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssApplyStepService.deleteBakssApplyStepByIds(ids));
    }
}
