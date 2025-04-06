package com.bakss.server.domain;

import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 【请填写功能名称】对象 bakss_apply_flow
 *
 * @author author
 * @date 2025-04-06
 */
@Data
public class BakssApplyFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** assign,leader,owner,dba,dbaLeader,admin */
    @Excel(name = "assign,leader,owner,dba,dbaLeader,admin")
    private String flowStep;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer flowOrder;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String applyId;

    /** 当前环节最后审批的人 */
    @Excel(name = "当前环节最后审批的人")
    private String reviewUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long reviewStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

}
