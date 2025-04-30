package com.bakss.veeam.domain.host;

import lombok.Data;

@Data
public class ViFolder {
    private  String id;
    private String name;
    private Long capacity;
    private Long freeSpace;
}
