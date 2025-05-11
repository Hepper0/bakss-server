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

    private Long id;

    private String appId;

    private String backupId;

    private String grantUser;

    private Integer expiration;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Long deleted;

}
