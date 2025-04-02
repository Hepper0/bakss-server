package com.bakss.server.core;

import com.bakss.server.domain.BakssApply;

/*
审批流程
提单人 => 直接上级 => DBA => 备份管理员
* */
public class Approval {

    final static int APPROVAL_STATUS_INIT = 0;
    final static int APPROVAL_STATUS_LEADER = 1;
    final static int APPROVAL_STATUS_DBA = 2;
    final static int APPROVAL_STATUS_MANAGER = 3;



}
