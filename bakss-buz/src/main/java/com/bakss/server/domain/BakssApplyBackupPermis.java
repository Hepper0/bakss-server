package com.bakss.server.domain;

import java.util.Date;

import com.bakss.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.bakss.common.annotation.Excel;
import lombok.Data;

/**
 * 备份权限申请对象 bakss_apply_backup_permis
 *
 * @author author
 * @date 2025-04-08
 */
@Data
public class BakssApplyBackupPermis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long appId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long backupId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String grantUser;

    private Integer expiration;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date startTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date endTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

}
