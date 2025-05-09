package com.bakss.veeam.controller;

import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.veeam.domain.job.BackupJobDetail;
import com.bakss.veeam.domain.job.BackupJobRO;
import com.bakss.veeam.service.VeeamJobService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/veeam/job/")
public class VeeamJobController extends BaseController {

    @Resource
    private VeeamJobService jobService;

    @GetMapping("list")
    public AjaxResult getBackupJobList(@RequestParam int page, @RequestParam int pageSize) {
        return success(jobService.getBackupJobList(page, pageSize));
    }

    @GetMapping("detail")
    public AjaxResult getJobDetail(@RequestParam String name) {
        return success(jobService.getJobDetail(name));
    }

    @PostMapping("start")
    public AjaxResult startJob(@RequestBody String name) {
        jobService.startJob(name);
        return success();
    }

    @PostMapping("stop")
    public AjaxResult stopJob(@RequestBody String name) {
        jobService.stopJob(name);
        return success();
    }

    @PostMapping("enable")
    public AjaxResult enableJob(@RequestBody String name) {
        jobService.enableJob(name);
        return success();
    }

    @PostMapping("disable")
    public AjaxResult disableJob(@RequestBody String name) {
        jobService.disableJob(name);
        return success();
    }

    @PostMapping("create")
    public AjaxResult createJob(@RequestBody BackupJobRO job) {
        jobService.createJob(job.getName(), job.getDescription(), job.getVmObjects(), job.getRepository(), job.getAfterJobName());
        return success();
    }

    @PostMapping("update")
    public AjaxResult updateJob(@RequestBody BackupJobDetail job) {
        jobService.updateJob(job);
        return success();
    }

    @DeleteMapping
    public AjaxResult deleteJob(@RequestBody String name) {
        jobService.deleteJob(name);
        return success();
    }
}
