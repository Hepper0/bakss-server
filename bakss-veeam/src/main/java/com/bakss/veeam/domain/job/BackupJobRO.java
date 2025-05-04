package com.bakss.veeam.domain.job;

import com.bakss.veeam.domain.host.ViEntity;
import lombok.Data;

import java.util.List;

@Data
public class BackupJobRO {
    String name;
    String description;
    List<ViEntity> vmObjects;
    String repository;
    String afterJobName;
}
