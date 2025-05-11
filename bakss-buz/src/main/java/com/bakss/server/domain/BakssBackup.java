package com.bakss.server.domain;

import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 备份对象对象 bakss_backup
 *
 * @author author
 * @date 2025-03-25
 */

@Data
public class BakssBackup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 备份软件 */
    @Excel(name = "备份软件", readConverterExp = "$column.readConverterExp()")
    private String backupSoftware;

    /** 软件版本 */
    @Excel(name = "软件版本", readConverterExp = "$column.readConverterExp()")
    private String softwareVersion;

    /** 客户端名称 */
    @Excel(name = "客户端名称", readConverterExp = "$column.readConverterExp()")
    private String clientName;

    /** 备份内容 */
    @Excel(name = "备份内容", readConverterExp = "$column.readConverterExp()")
    private String backupContent;

    /** 备份IP */
    @Excel(name = "备份IP", readConverterExp = "$column.readConverterExp()")
    private String backupIP;

    /** 应用名称 */
    @Excel(name = "应用名称", readConverterExp = "$column.readConverterExp()")
    private String appName;

    /* 环境 */
    private String env;

    /** 系统类型 */
    @Excel(name = "系统类型", readConverterExp = "$column.readConverterExp()")
    private String platform;

    /** 负责人 */
    @Excel(name = "负责人", readConverterExp = "$column.readConverterExp()")
    private String owner;

    private String manager;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    private String backupPort;

    private String backupServer;

    private String costType;

    private String costNumber;

    private String machineType;

    private String dataCenter;

    private String backupJobKey;

}
