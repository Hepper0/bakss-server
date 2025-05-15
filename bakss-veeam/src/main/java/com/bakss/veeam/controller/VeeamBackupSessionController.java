package com.bakss.veeam.controller;

import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.veeam.service.VeeamSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/veeam/jobSession/")
public class VeeamBackupSessionController extends BaseController {

    @Resource
    private VeeamSessionService sessionService;

    @GetMapping("list")
    public AjaxResult getVeeamHostList(@RequestParam int page, @RequestParam int pageSize) {
        return success(sessionService.getBackupSessionList(page, pageSize, getBackupServerHost()));
    }

    @GetMapping("detail")
    public AjaxResult getViEntity(@RequestParam String ID) {
        return success(sessionService.getBackupSessionDetail(ID, getBackupServerHost()));
    }
}
