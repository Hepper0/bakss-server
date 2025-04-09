package com.bakss.server.domain;

import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 申请步骤对象 bakss_App_step
 *
 * @author author
 * @date 2025-04-06
 */
@Data
public class BakssAppStep extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long appType;

    /** assign,leader,owner,dba,dbaLeader,admin */
    @Excel(name = "assign,leader,owner,dba,dbaLeader,admin")
    private String appSteps;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;
}
