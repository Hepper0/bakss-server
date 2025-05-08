package com.bakss.server.domain.backup;

import com.bakss.common.annotation.Excel;
import lombok.Data;

@Data
public class VMware {
    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String repository;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vmObjects;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String afterJob;
}
