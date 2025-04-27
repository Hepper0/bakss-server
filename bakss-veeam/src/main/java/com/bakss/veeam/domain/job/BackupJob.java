package com.bakss.veeam.domain.job;

import lombok.Data;

@Data
public class BackupJob {
    private String ID;
    private String type;
    private String name;
    private Integer jobSourceType;
    private Integer latestResult;
    private String description;
    private Boolean scheduleEnabled;
    private String creationDateUtc;
    private String sessionId;
    private String sessionCreationTime;
    private String sessionEndTime;
    private Integer sessionState;
    private Integer sessionResult;
    private Boolean sessionRunManually;
    private Integer sessionProgress;
    private Integer sessionAvgSpeed;
    private String repositoryName;
}
