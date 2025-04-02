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
import com.bakss.server.domain.BakssApplyFlow;
import com.bakss.server.service.IBakssApplyFlowService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 申请流程Controller
 *
 * @author author
 * @date 2025-04-02
 */
@RestController
@RequestMapping("/service/flow")
public class BakssApplyFlowController extends BaseController
{
    @Resource
    private IBakssApplyFlowService bakssApplyFlowService;

    /**
     * 查询申请流程列表
     */
    @PreAuthorize("@ss.hasPermi('service:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApplyFlow bakssApplyFlow)
    {
        startPage();
        List<BakssApplyFlow> list = bakssApplyFlowService.selectBakssApplyFlowList(bakssApplyFlow);
        return getDataTable(list);
    }

    /**
     * 导出申请流程列表
     */
    @PreAuthorize("@ss.hasPermi('service:flow:export')")
    @Log(title = "申请流程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApplyFlow bakssApplyFlow)
    {
        List<BakssApplyFlow> list = bakssApplyFlowService.selectBakssApplyFlowList(bakssApplyFlow);
        ExcelUtil<BakssApplyFlow> util = new ExcelUtil<BakssApplyFlow>(BakssApplyFlow.class);
        util.exportExcel(response, list, "申请流程数据");
    }

    /**
     * 获取申请流程详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:flow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssApplyFlowService.selectBakssApplyFlowById(id));
    }

    /**
     * 新增申请流程
     */
    @PreAuthorize("@ss.hasPermi('service:flow:add')")
    @Log(title = "申请流程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApplyFlow bakssApplyFlow)
    {
        return toAjax(bakssApplyFlowService.insertBakssApplyFlow(bakssApplyFlow));
    }

    /**
     * 修改申请流程
     */
    @PreAuthorize("@ss.hasPermi('service:flow:edit')")
    @Log(title = "申请流程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApplyFlow bakssApplyFlow)
    {
        return toAjax(bakssApplyFlowService.updateBakssApplyFlow(bakssApplyFlow));
    }

    /**
     * 删除申请流程
     */
    @PreAuthorize("@ss.hasPermi('service:flow:remove')")
    @Log(title = "申请流程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssApplyFlowService.deleteBakssApplyFlowByIds(ids));
    }
}
