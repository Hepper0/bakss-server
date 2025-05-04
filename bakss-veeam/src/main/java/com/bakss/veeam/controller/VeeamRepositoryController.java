package com.bakss.veeam.controller;


import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.veeam.service.VeeamProxyService;
import com.bakss.veeam.service.VeeamRepositoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/veeam/repository/")
public class VeeamRepositoryController extends BaseController {

    @Resource
    private VeeamRepositoryService repositoryService;

    @GetMapping("list")
    public AjaxResult getVeeamRepositoryList(@RequestParam int page, @RequestParam int pageSize) {
        return success(repositoryService.getVeeamRepositoryList(page, pageSize));
    }

    @GetMapping("detail")
    public AjaxResult getVeeamRepositoryDetail(@RequestParam String ID) {
        return success(repositoryService.getVeeamRepositoryDetail(ID));
    }

    @DeleteMapping
    public AjaxResult deleteVeeamRepository(@RequestParam String name) {
        repositoryService.deleteVeeamRepository(name);
        return success();
    }
}
