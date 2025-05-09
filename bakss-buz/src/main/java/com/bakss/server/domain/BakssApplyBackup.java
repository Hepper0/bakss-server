package com.bakss.server.domain;

import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

/**
 *  申请创建备份对象 bakss_apply_backup
 *
 * @author author
 * @date 2025-05-06
 */
@Data
public class BakssApplyBackup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String appId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String description;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    private String backupContent;

    private String machineType;

    private String dataCenter;

    private String env;

    private String platform;

    private String appName;

    private String backupSoftware;

    private JSONObject backupInfo;

    private String costType;

    private String costNumber;

}
