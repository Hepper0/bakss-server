package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.host.VeeamHost;
import com.bakss.veeam.domain.host.ViResourcePool;
import com.bakss.veeam.domain.session.BackupSession;
import com.bakss.veeam.domain.session.BackupSessionDetail;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VeeamSessionService {

    @Resource
    VeeamBasicService basicService;

    public List<BackupSession> getBackupSessionList(String jobName, Integer page, Integer pageSize, String server) {
        String token = basicService.validate(server);
        String path = "/jobsessions/getJobsessionList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("jobName", jobName);
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get( server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray hostList = data.getJSONArray("list");
        List<BackupSession> sessionList = new ArrayList<>();
        if (hostList.size() > 0) {
            for (Object host: hostList) {
                BackupSession veeamHost = BeanUtils.mapToBean((JSONObject)host, BackupSession.class);
                sessionList.add(veeamHost);
            }
        }
        return sessionList;
    }

    public BackupSessionDetail getBackupSessionDetail(String ID, String server) {
        String token = basicService.validate(server);
        String path = "/jobsessions/findJobsession";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("ID", ID);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONObject jobSession = data.getJSONObject("jobSession");
        return BeanUtils.mapToBean(jobSession, BackupSessionDetail.class);
    }
}
