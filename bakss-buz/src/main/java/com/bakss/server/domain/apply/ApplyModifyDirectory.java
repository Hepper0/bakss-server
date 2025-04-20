package com.bakss.server.domain.apply;

import lombok.Data;

@Data
public class ApplyModifyDirectory extends ApplyBase {
    int backupId;
    String oldDirectory;
    String newDirectory;
}
