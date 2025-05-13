package com.bakss.veeam.domain.host;

import lombok.Data;

@Data
public class ViSwitch extends HostBase {
    private  String id;
    private String name;
    private Long capacity;
    private Long freeSpace;
}
