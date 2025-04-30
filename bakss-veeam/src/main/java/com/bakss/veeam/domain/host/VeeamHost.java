package com.bakss.veeam.domain.host;

import lombok.Data;

@Data
public class VeeamHost {
    private String ID;
    private Integer type;
    private String name;
    private String parentId;
    private String description;
    private String physicalHostId;
    private String credentialsId;
    private String options;
}
