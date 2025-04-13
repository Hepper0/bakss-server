package com.bakss.server.domain;

import java.util.Date;
import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 申请流程对象 bakss_App
 *
 * @author author
 * @date 2025-04-02
 */
@Data
public class BakssApp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer appType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String appUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String backupId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date backupTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long backupStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long flowId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    private Long status;
}
