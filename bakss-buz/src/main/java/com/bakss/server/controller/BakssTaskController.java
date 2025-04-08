package com.bakss.server.controller;

import com.bakss.common.annotation.Log;
import com.bakss.common.core.controller.BaseController;
import com.bakss.common.core.domain.AjaxResult;
import com.bakss.common.core.page.TableDataInfo;
import com.bakss.common.enums.BusinessType;
import com.bakss.common.utils.poi.ExcelUtil;
import com.bakss.server.domain.BakssApply;
import com.bakss.server.service.IBakssApplyService;
import com.bakss.server.service.IBakssTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @GetMapping("/todo")
    public TableDataInfo todo()
    {
        return getDataTable(taskService.todo());
    }

    /**
     * 已完成申请
     */
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @GetMapping("/done")
    public TableDataInfo done()
    {
        return getDataTable(taskService.done());
    }
}
