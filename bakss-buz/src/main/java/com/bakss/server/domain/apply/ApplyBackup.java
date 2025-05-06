package com.bakss.server.domain.apply;

import com.bakss.veeam.domain.host.ViEntity;
import lombok.Data;

import java.util.List;

@Data
public class ApplyBackup extends ApplyBase {
    String name;
    String description;
    List<String> vmObjects;
    String repository;
    String afterJobName;
}
