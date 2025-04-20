package com.bakss.server.service;

import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.apply.ApplyPermission;
import com.bakss.server.domain.apply.ApplyStrategy;

public interface IBakssApplyService {

    public void addBackupPermissionApplication(ApplyPermission applyPermission);

    public void addBackupOnce(BakssApp bakssApp);

    public void modifyBackupStrategy(ApplyStrategy strategy);
}
