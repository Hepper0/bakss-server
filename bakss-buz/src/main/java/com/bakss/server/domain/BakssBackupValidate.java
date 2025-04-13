package com.bakss.server.domain;

import java.util.Date;

import com.bakss.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.bakss.common.annotation.Excel;
import lombok.Data;

/**
 * 备份授权有效期对象 bakss_backup_validate
 *
 * @author author
 * @date 2025-04-14
 */

@Data
public class BakssBackupValidate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long backupId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String username;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long expType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date startDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date endDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;
}
