package com.bakss.server.service;

import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.apply.ApplyChangeUser;
import com.bakss.server.domain.apply.ApplyPermission;
import com.bakss.server.domain.apply.ApplyStrategy;

public interface IBakssApplyService {

    public void applyBackupPermission(ApplyPermission applyPermission);

    public void applyBackupOnce(BakssApp bakssApp);

    public void applyModifyBackupStrategy(ApplyStrategy strategy);

    public void addBackupChangeUser(ApplyChangeUser changeUser);
}
