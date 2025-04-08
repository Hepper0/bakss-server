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
import com.bakss.server.domain.BakssApplyStrategy;
import com.bakss.server.service.IBakssApplyStrategyService;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.common.core.page.TableDataInfo;

/**
 * 备份策略申请Controller
 *
 * @author author
 * @date 2025-04-08
 */
@RestController
@RequestMapping("/service/strategy")
public class BakssApplyStrategyController extends BaseController
{
    @Resource
    private IBakssApplyStrategyService bakssApplyStrategyService;

    /**
     * 查询备份策略申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:strategy:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssApplyStrategy bakssApplyStrategy)
    {
        startPage();
        List<BakssApplyStrategy> list = bakssApplyStrategyService.selectBakssApplyStrategyList(bakssApplyStrategy);
        return getDataTable(list);
    }

    /**
     * 导出备份策略申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:strategy:export')")
    @Log(title = "备份策略申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssApplyStrategy bakssApplyStrategy)
    {
        List<BakssApplyStrategy> list = bakssApplyStrategyService.selectBakssApplyStrategyList(bakssApplyStrategy);
        ExcelUtil<BakssApplyStrategy> util = new ExcelUtil<BakssApplyStrategy>(BakssApplyStrategy.class);
        util.exportExcel(response, list, "备份策略申请数据");
    }

    /**
     * 获取备份策略申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:strategy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssApplyStrategyService.selectBakssApplyStrategyById(id));
    }

    /**
     * 新增备份策略申请
     */
    @PreAuthorize("@ss.hasPermi('service:strategy:add')")
    @Log(title = "备份策略申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssApplyStrategy bakssApplyStrategy)
    {
        return toAjax(bakssApplyStrategyService.insertBakssApplyStrategy(bakssApplyStrategy));
    }

    /**
     * 修改备份策略申请
     */
    @PreAuthorize("@ss.hasPermi('service:strategy:edit')")
    @Log(title = "备份策略申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssApplyStrategy bakssApplyStrategy)
    {
        return toAjax(bakssApplyStrategyService.updateBakssApplyStrategy(bakssApplyStrategy));
    }

    /**
     * 删除备份策略申请
     */
    @PreAuthorize("@ss.hasPermi('service:strategy:remove')")
    @Log(title = "备份策略申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssApplyStrategyService.deleteBakssApplyStrategyByIds(ids));
    }
}
