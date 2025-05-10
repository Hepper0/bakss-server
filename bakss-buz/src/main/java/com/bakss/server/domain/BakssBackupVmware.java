package com.bakss.server.domain;

import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class BakssBackupVmware extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;


    private String backupId;


    private String vCenter;


    private String vmObjects;


    private String repository;


    private String afterJob;


    private Long deleted;

}
