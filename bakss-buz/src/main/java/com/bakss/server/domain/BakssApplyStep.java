package com.bakss.server.domain;

import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 【请填写功能名称】对象 bakss_apply_step
 *
 * @author author
 * @date 2025-04-06
 */
@Data
public class BakssApplyStep extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long applyType;

    /** assign,leader,owner,dba,dbaLeader,admin */
    @Excel(name = "assign,leader,owner,dba,dbaLeader,admin")
    private String applySteps;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;
}
