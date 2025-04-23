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
import com.bakss.server.domain.BakssAppFlow;
import com.bakss.server.service.IBakssAppFlowService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 申请流程Controller
 *
 * @author author
 * @date 2025-04-02
 */
@RestController
@RequestMapping("/service/application/flow")
public class BakssAppFlowController extends BaseController
{
    @Resource
    private IBakssAppFlowService bakssAppFlowService;

    /**
     * 查询申请流程列表
     */
    @PreAuthorize("@ss.hasPermi('service:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssAppFlow bakssAppFlow)
    {
        startPage();
        List<BakssAppFlow> list = bakssAppFlowService.selectBakssAppFlowList(bakssAppFlow);
        return getDataTable(list);
    }

    /**
     * 导出申请流程列表
     */
    @PreAuthorize("@ss.hasPermi('service:flow:export')")
    @Log(title = "申请流程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssAppFlow bakssAppFlow)
    {
        List<BakssAppFlow> list = bakssAppFlowService.selectBakssAppFlowList(bakssAppFlow);
        ExcelUtil<BakssAppFlow> util = new ExcelUtil<BakssAppFlow>(BakssAppFlow.class);
        util.exportExcel(response, list, "申请流程数据");
    }

    /**
     * 获取申请流程详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:flow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssAppFlowService.selectBakssAppFlowById(id));
    }

    /**
     * 新增申请流程
     */
    @PreAuthorize("@ss.hasPermi('service:flow:add')")
    @Log(title = "申请流程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssAppFlow bakssAppFlow)
    {
        return toAjax(bakssAppFlowService.insertBakssAppFlow(bakssAppFlow));
    }

    /**
     * 修改申请流程
     */
    @PreAuthorize("@ss.hasPermi('service:flow:edit')")
    @Log(title = "申请流程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssAppFlow bakssAppFlow)
    {
        return toAjax(bakssAppFlowService.updateBakssAppFlow(bakssAppFlow));
    }

    /**
     * 删除申请流程
     */
    @PreAuthorize("@ss.hasPermi('service:flow:remove')")
    @Log(title = "申请流程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssAppFlowService.deleteBakssAppFlowByIds(ids));
    }
}
