package com.bakss.server.config;

import java.util.Arrays;
import java.util.List;

public interface Config {
    // 申请单状态
    public final  Long APPLICATION_CANCELLED = 2L;
    public final  Long APPLICATION_COMPLETED = 3L;

    // 审批步骤状态
    public final  Long APPROVAL_APPROVED = 1L;
    public final  Long APPROVAL_REJECTED = 2L;

    // 申请审核步骤
    final  String APPROVAL_STATUS_LEADER = "leader";  // 直接上级审批
    final  String APPROVAL_STATUS_OWNER = "owner";  // 直接上级审批
    final  String APPROVAL_STATUS_ASSIGN = "assign";  // 直接上级审批
    final  String APPROVAL_STATUS_DBA = "dba";  // DBA审批
    final  String APPROVAL_STATUS_DBA_LEADER = "dbaLeader";  // DBA审批
    final  String APPROVAL_STATUS_MANAGER = "admin"; // 备份管理员审批

    // 申请类型
     Integer APPLY_BACKUP_PERMISSION = 0;
     Integer GRANT_BACKUP_PERMISSION = 1;
     Integer CREATE_RESTORE = 2;
     Integer CREATE_BACKUP = 3;
     Integer BACKUP_RIGHT_NOW = 4;
     Integer BACKUP_AT_TIME = 5;
     Integer MODIFY_DIRECTORY = 6;
     Integer ENABLE_STRATEGY = 7;
     Integer DISABLE_STRATEGY = 8;
     Integer DELETE_STRATEGY = 9;
     Integer MODIFY_OWNER = 10;
     Integer MODIFY_MANAGER = 11;

    final  List<String> DB_TYPES = Arrays.asList("MySQL", "SQLSERVER", "PostgreSQL", "Oracle");
}
