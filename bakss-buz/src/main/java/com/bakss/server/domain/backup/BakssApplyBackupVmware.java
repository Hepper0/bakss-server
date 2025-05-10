package com.bakss.server.domain.backup;

import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * Apply Backup VM对象 bakss_apply_backup_vmware
 *
 * @author author
 * @date 2025-05-10
 */
@Data
public class BakssApplyBackupVmware extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String appId;

    private String vCenter;

    private String vmObjects;

    private String repository;
    
    private String afterJob;

    private Long deleted;
}
