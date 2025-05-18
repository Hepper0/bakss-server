package com.bakss.veeam.domain.job.schedule;

import lombok.Data;

@Data
public class ApplyBackupJobScheduleMonthly {
    String time;
    String dayNumberInMonth;
    String dayOfWeek;
    String dayOfMonth;
    String[] months;
}
