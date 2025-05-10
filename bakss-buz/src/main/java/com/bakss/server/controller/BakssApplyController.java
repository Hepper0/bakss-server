package com.bakss.server.controller;

import com.bakss.common.annotation.Log;
import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.common.enums.BusinessType;
import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.applyRO.*;
import com.bakss.server.service.IBakssApplyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Resource
    private IBakssApplyService bakssApplyService;

    /**
     * 查询申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/permission")
    public AjaxResult permission(@RequestBody ApplyPermission applyPermission)
    {
        bakssApplyService.applyBackupPermission(applyPermission);
        return success();
    }

    /**
     * 导出申请列表
     */
    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/modifyDirectory")
    public AjaxResult modifyDirectory(@RequestBody ApplyModifyDirectory modifyDirectory)
    {
        bakssApplyService.applyModifyBackupDirectory(modifyDirectory);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/createBackup")
    public AjaxResult createBackup(@RequestBody ApplyBackup backup)
    {
        bakssApplyService.applyBackup(backup);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/createRestore")
    public AjaxResult createRestore(@RequestBody ApplyRestore restore)
    {
        bakssApplyService.applyRestore(restore);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/strategy")
    public AjaxResult strategy(@RequestBody ApplyStrategy strategy)
    {
        bakssApplyService.applyModifyBackupStrategy(strategy);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/changeUser")
    public AjaxResult changeUser(@RequestBody ApplyChangeUser changeUser)
    {
        bakssApplyService.applyChangeUser(changeUser);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/backup")
    public AjaxResult backup(@RequestBody BakssApp bakssApp)
    {
        this.bakssApplyService.applyBackupOnce(bakssApp);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('service:apply:add')")
    @Log(title = "申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/cancel/{appId}")
    public AjaxResult cancel(@PathVariable String appId)
    {
        this.bakssApplyService.cancelApplication(appId);
        return success();
    }

}
