package com.bakss.server.service;

import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.apply.*;

public interface IBakssApplyService {

    public void applyBackupPermission(ApplyPermission applyPermission);

    public void applyBackupOnce(BakssApp bakssApp);

    public void applyModifyBackupStrategy(ApplyStrategy strategy);

    public void applyModifyBackupDirectory(ApplyModifyDirectory directory);

    public void applyChangeUser(ApplyChangeUser changeUser);

    public void applyBackup(ApplyBackup applyBackup);

    public void applyRestore(ApplyRestore applyBackup);
}
