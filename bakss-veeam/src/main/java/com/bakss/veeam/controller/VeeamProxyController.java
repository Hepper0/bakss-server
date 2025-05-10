package com.bakss.veeam.controller;

import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.veeam.service.VeeamProxyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/veeam/proxy/")
public class VeeamProxyController extends BaseController {

    @Resource
    private VeeamProxyService proxyService;

    @GetMapping("list")
    public AjaxResult getBackupProxyList(@RequestParam int page, @RequestParam int pageSize) {
        return success(proxyService.getBackupProxyList(page, pageSize, getBackupServerHost()));
    }

    @GetMapping("detail")
    public AjaxResult getBackupProxyDetail(@RequestParam String ID) {
        return success(proxyService.getBackupProxyDetail(ID, getBackupServerHost()));
    }

    @DeleteMapping
    public AjaxResult deleteBackupProxy(@RequestParam String name) {
        proxyService.deleteBackupProxy(name, getBackupServerHost());
        return success();
    }
}
