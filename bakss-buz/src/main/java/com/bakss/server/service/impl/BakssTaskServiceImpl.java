package com.bakss.server.service.impl;

import com.bakss.common.core.domain.entity.SysRole;
import com.bakss.common.core.domain.model.LoginUser;
import com.bakss.common.utils.SecurityUtils;
import com.bakss.server.domain.BakssTask;
import com.bakss.server.mapper.BakssTaskMapper;
import com.bakss.server.service.IBakssTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BakssTaskServiceImpl implements IBakssTaskService {

    private static final Logger log = LoggerFactory.getLogger(BakssTaskServiceImpl.class);

    @Resource
    private BakssTaskMapper taskMapper;

    public List<BakssTask> todo(){
        LoginUser user = SecurityUtils.getLoginUser();
        if (user.getUser().isAdmin()) {
            return taskMapper.getAllTodoTaskList();
        }
        List<BakssTask> result = new ArrayList<>();
        List<SysRole> roles = user.getUser().getRoles();
        List<BakssTask> dbaTask = null;
        List<BakssTask> backupAdminTask = null;
        for (SysRole role : roles) {
            String roleName = role.getRoleName();
            if (roleName.equals("dba")) {
                dbaTask = taskMapper.getDbaTodoTaskList();
                break;
            } else if (roleName.equals("backupAdmin")){
                backupAdminTask = taskMapper.getAdminTodoTaskList();
            }
        }
        // todo 获取查询当前用户的下级 与 dbaLeader的代办
        List<BakssTask> leaderTask = taskMapper.getLeaderTodoTaskList();
        List<BakssTask> assignTask = taskMapper.getAssignTodoTaskList(user.getUsername());
        List<BakssTask> ownerTask = taskMapper.getOwnerTodoTaskList(user.getUsername());
        if (dbaTask != null) {
            result.addAll(dbaTask);
        }
        if(backupAdminTask != null) {
            result.addAll(backupAdminTask);
        }
        if(leaderTask != null) {
            result.addAll(leaderTask);
        }
        if(assignTask != null) {
            result.addAll(assignTask);
        }
        if(ownerTask != null) {
            result.addAll(ownerTask);
        }
        return result;
    }

    public List<BakssTask> done(){
        return taskMapper.getDoneTaskList();
    }
}
