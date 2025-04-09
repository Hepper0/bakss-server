package com.bakss.server.mapper;

import com.bakss.server.domain.BakssApp;
import com.bakss.server.domain.BakssTask;

import java.util.List;

/**
 * 申请Mapper接口
 * 
 * @author author
 * @date 2025-03-26
 */
public interface BakssTaskMapper {
    List<BakssTask> getLeaderTodoTaskList();
    List<BakssTask> getOwnerTodoTaskList(String owner);
    List<BakssTask> getDbaLeaderTodoTaskList();
    List<BakssTask> getAdminTodoTaskList();
    List<BakssTask> getDbaTodoTaskList();
    List<BakssTask> getAssignTodoTaskList(String user);
    List<BakssTask> getAllTodoTaskList();
    List<BakssTask> getDoneTaskList();
}
