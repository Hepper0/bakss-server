package com.bakss.veeam.domain.job;

import com.bakss.veeam.domain.host.ViEntity;
import com.bakss.veeam.domain.job.schedule.ApplyBackupJobScheduleContinuous;
import com.bakss.veeam.domain.job.schedule.ApplyBackupJobScheduleDaily;
import com.bakss.veeam.domain.job.schedule.ApplyBackupJobScheduleMonthly;
import com.bakss.veeam.domain.job.schedule.ApplyBackupJobSchedulePeriodically;
import lombok.Data;

import java.util.List;

@Data
public class ApplyBackupJob {
    String name;
    String description;
    String repository;
    String afterJobName;
    List<ViEntity> vmObjects;
    Boolean isScheduleEnable;
    String policy;
    ApplyBackupJobScheduleDaily scheduleDaily;
    ApplyBackupJobScheduleMonthly scheduleMonthly;
    ApplyBackupJobSchedulePeriodically schedulePeriodically;
    ApplyBackupJobScheduleContinuous scheduleContinuous;
}
