package com.bakss.veeam.domain.backup;

import lombok.Data;

@Data
public class VMPoint {
    private String creationTime;
    private String id;
    private Long size;
    private Integer type;
    private String vmName;
}
