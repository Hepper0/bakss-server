package com.bakss.server.controller;

import com.bakss.common.annotation.Log;
import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.page.TableDataInfo;
import com.bakss.common.enums.BusinessType;
import com.bakss.server.service.IBakssTaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 申请Controller
 *
 * @author author
 * @date 2025-03-26
 */
@RestController
@RequestMapping("/service/task")
public class BakssTaskController extends BaseController
{
    @Resource
    private IBakssTaskService taskService;

    /**
     * 代办申请
     */
    @GetMapping("/todo")
    public TableDataInfo todo()
    {
        return getDataTable(taskService.todo());
    }

    /**
     * 已完成申请
     */
    @GetMapping("/done")
    public TableDataInfo done()
    {
        return getDataTable(taskService.done());
    }

    @GetMapping("/list")
    public TableDataInfo list()
    {
        return getDataTable(taskService.list());
    }
}
