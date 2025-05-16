package com.bakss.veeam.domain.session;

import lombok.Data;

@Data
public class BackupSession {
    private String creationTime;
    private String endTime;
    private String ID;
    private String jobId;
    private Integer jobType;
    private Integer state;
    private Integer result;
    private Integer progress;
    private Boolean runManually;
    private String jobName;
    private String description;
    private String operation;
    private String reason;
}
