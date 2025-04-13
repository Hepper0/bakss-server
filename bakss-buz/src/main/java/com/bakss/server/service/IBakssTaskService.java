package com.bakss.server.service;

import com.bakss.server.domain.BakssTask;

import java.util.List;

public interface IBakssTaskService {
    public List<BakssTask> todo();

    public List<BakssTask> done();

    public List<BakssTask> list();
}
