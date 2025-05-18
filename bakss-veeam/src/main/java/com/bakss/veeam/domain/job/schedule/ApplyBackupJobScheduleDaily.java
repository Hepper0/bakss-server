package com.bakss.veeam.domain.job.schedule;

import lombok.Data;

@Data
public class ApplyBackupJobScheduleDaily {
    String startDateTimeLocal;
    String dayNumberInMonth;
    String[] dayOfWeek;
}
