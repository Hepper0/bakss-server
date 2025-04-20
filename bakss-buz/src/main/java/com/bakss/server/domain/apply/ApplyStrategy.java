package com.bakss.server.domain.apply;

import lombok.Data;

@Data
public class ApplyStrategy extends ApplyBase {
    private Integer strategyId;
    private Integer backupId;
    private Integer type;  // 1 enable 2 disable 3 delete
}
