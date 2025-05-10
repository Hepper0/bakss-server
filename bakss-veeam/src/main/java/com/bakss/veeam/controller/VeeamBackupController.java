package com.bakss.veeam.controller;


import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.veeam.service.VeeamBackupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;


@RestController
@RequestMapping("/veeam/backup/")
public class VeeamBackupController extends BaseController {

    @Resource
    private VeeamBackupService backupService;

    @GetMapping("list")
    public AjaxResult getBackupList(@RequestParam int page, @RequestParam int pageSize) {
        return success(backupService.getBackupList(page, pageSize, getBackupServerHost()));
    }

    @GetMapping("child")
    public AjaxResult getChildBackupList(@RequestParam int parentId) {
        return success(backupService.getChildBackupList(parentId, getBackupServerHost()));
    }

    @GetMapping("application")
    public AjaxResult getApplicationByName(@RequestParam String[] Names) {
        return success(backupService.getApplicationByName(Names, getBackupServerHost()));
    }

    @GetMapping("detail")
    public AjaxResult getBackupDetail(@RequestParam String ID) {
        return success(backupService.getBackupDetail(ID, getBackupServerHost()));
    }

    @GetMapping("pointDetail")
    public AjaxResult getVMPointDetail(@RequestParam String ID, @RequestParam String backupId) {
        return success(backupService.getVMPointDetail(ID, backupId, getBackupServerHost()));
    }

    @GetMapping("point")
    public AjaxResult getVMPoints(@RequestParam String backupId, @RequestParam String vmName) {
        return success(backupService.getVMPoints(backupId, vmName, getBackupServerHost()));
    }
}
