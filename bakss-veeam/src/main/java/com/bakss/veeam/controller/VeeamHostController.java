package com.bakss.veeam.controller;

import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.common.utils.spring.SpringUtils;
import com.bakss.veeam.domain.host.ViDataStore;
import com.bakss.veeam.service.VeeamHostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/veeam/host/")
public class VeeamHostController extends BaseController {

    @Resource
    private VeeamHostService hostService;


    @GetMapping("list")
    public AjaxResult getVeeamHostList(@RequestParam int page, @RequestParam int pageSize) {
        return success(hostService.getVeeamHostList(page, pageSize, getBackupServerHost()));
    }

    @GetMapping("entity")
    public AjaxResult getViEntity(@RequestParam String serverName, @RequestParam String viewMode) {
        return success(hostService.getViEntityList(serverName, viewMode, getBackupServerHost()));
    }

    @GetMapping("datastore")
    public AjaxResult getViDatastore(@RequestParam String serverName) {
        return success(hostService.getViDatastore(serverName, getBackupServerHost()));
    }

    @GetMapping("resourcePool")
    public AjaxResult getViResourcePool(@RequestParam String serverName) {
        return success(hostService.getViResourcePool(serverName, getBackupServerHost()));
    }

    @GetMapping("switch")
    public AjaxResult getViSwitch(@RequestParam String serverName) {
        return success(hostService.getViSwitch(serverName, getBackupServerHost()));
    }

    @GetMapping("network")
    public AjaxResult getViNetwork(@RequestParam String serverName) {
        return success(hostService.getViNetwork(serverName, getBackupServerHost()));
    }

    @GetMapping("folder")
    public AjaxResult getViFolder(@RequestParam String serverName) {
        return success(hostService.getViFolder(serverName, getBackupServerHost()));
    }
}
