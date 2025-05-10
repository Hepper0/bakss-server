package com.bakss.server.domain.backup;

import lombok.Data;

@Data
public class VMware {

    private String appId;

    private String vCenter;

    private String vmObjects;

    private String repository;

    private String afterJob;
}
