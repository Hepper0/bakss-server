package com.bakss.veeam.controller;


import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.veeam.service.VeeamProxyService;
import com.bakss.veeam.service.VeeamRepositoryService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/veeam/repository/")
public class VeeamRepositoryController extends BaseController {

    @Resource
    private VeeamRepositoryService repositoryService;

    @GetMapping("list")
    public AjaxResult getVeeamRepositoryList(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) Boolean useCache) {
        if (useCache != null) {
            return success(repositoryService.getVeeamRepositoryList(page, pageSize, getBackupServerHost(), useCache));
        } else {
            return success(repositoryService.getVeeamRepositoryList(page, pageSize, getBackupServerHost()));
        }

    }

    @GetMapping("detail")
    public AjaxResult getVeeamRepositoryDetail(@RequestParam String ID) {
        return success(repositoryService.getVeeamRepositoryDetail(ID, getBackupServerHost()));
    }

    @DeleteMapping
    public AjaxResult deleteVeeamRepository(@RequestParam String name) {
        repositoryService.deleteVeeamRepository(name, getBackupServerHost());
        return success();
    }
}
