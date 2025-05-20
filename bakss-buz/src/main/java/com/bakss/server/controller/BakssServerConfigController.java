package com.bakss.server.controller;

import com.bakss.common.annotation.Log;
import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.common.core.page.TableDataInfo;
import com.bakss.common.enums.BusinessType;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.server.domain.BakssServerConfig;
import com.bakss.server.service.IBakssServerConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * VeeamServerController
 *
 * @author author
 * @date 2025-05-15
 */
@RestController
@RequestMapping("/veeam/serverConfig")
public class BakssServerConfigController extends BaseController
{
    @Resource
    private IBakssServerConfigService bakssServerConfigService;

    /**
     * 查询VeeamServer列表
     */
    @PreAuthorize("@ss.hasPermi('veeam:server:list')")
    @GetMapping("/list")
    public TableDataInfo list(BakssServerConfig bakssServerConfig)
    {
        startPage();
        List<BakssServerConfig> list = bakssServerConfigService.selectBakssServerConfigList(bakssServerConfig);
        return getDataTable(list);
    }

    /**
     * 导出VeeamServer列表
     */
    @PreAuthorize("@ss.hasPermi('veeam:server:export')")
    @Log(title = "VeeamServer", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BakssServerConfig bakssServerConfig)
    {
        List<BakssServerConfig> list = bakssServerConfigService.selectBakssServerConfigList(bakssServerConfig);
        ExcelUtil<BakssServerConfig> util = new ExcelUtil<BakssServerConfig>(BakssServerConfig.class);
        util.exportExcel(response, list, "VeeamServer数据");
    }

    /**
     * 获取VeeamServer详细信息
     */
    @PreAuthorize("@ss.hasPermi('veeam:server:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bakssServerConfigService.selectBakssServerConfigById(id));
    }

    /**
     * 新增VeeamServer
     */
    @PreAuthorize("@ss.hasPermi('veeam:server:add')")
    @Log(title = "VeeamServer", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BakssServerConfig bakssServerConfig)
    {
        return toAjax(bakssServerConfigService.insertBakssServerConfig(bakssServerConfig));
    }

    /**
     * 修改VeeamServer
     */
    @PreAuthorize("@ss.hasPermi('veeam:server:edit')")
    @Log(title = "VeeamServer", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BakssServerConfig bakssServerConfig)
    {
        return toAjax(bakssServerConfigService.updateBakssServerConfig(bakssServerConfig));
    }

    /**
     * 删除VeeamServer
     */
    @PreAuthorize("@ss.hasPermi('veeam:server:remove')")
    @Log(title = "VeeamServer", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bakssServerConfigService.deleteBakssServerConfigByIds(ids));
    }
}
