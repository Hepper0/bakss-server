package com.bakss.server.controller;

import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.annotation.Log;
import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.common.core.page.TableDataInfo;
import com.bakss.common.enums.BusinessType;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.server.domain.BakssApp;
import com.bakss.server.service.IBakssAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    private IBakssAppService bakssAppService;

    /**
     * 查询申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/permission")
    public AjaxResult permission(JSONObject requestJson)
    {
        return success();
    }

    /**
     * 导出申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/modifyDirectory")
    public AjaxResult modifyDirectory(JSONObject requestJson)
    {
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/createBackup")
    public AjaxResult createBackup(JSONObject requestJson)
    {
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/createRestore")
    public AjaxResult createRestore(JSONObject requestJson)
    {
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/strategy")
    public AjaxResult strategy(JSONObject requestJson)
    {
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/modifyUser")
    public AjaxResult modifyUser(JSONObject requestJson)
    {
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/backup")
    public AjaxResult backup(JSONObject requestJson)
    {
        return success();
    }

}
