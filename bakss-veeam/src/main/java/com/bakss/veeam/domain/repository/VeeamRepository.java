package com.bakss.veeam.domain.repository;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class VeeamRepository {
    private String ID;
    private Integer type;
    private String hostId;
    private String options;
    private Long freeSpace;
    private Long totalSpace;
    private Integer extentType;
    private Integer status;
    private String name;
    private String description;
    private String path;
    private String hostName;
    private String parentRepId;
    private Boolean isRotatedDriveRepository;
    private String mountHostId;
    private JSONObject optionsSet;
    private String extendOptionsSet;
    private Boolean enableBackupImmutability;
    private Integer immutabilityPeriod;
}
