package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.job.BackupJob;
import com.bakss.veeam.domain.job.BackupJobDetail;
import com.bakss.veeam.domain.login.LoginRequest;
import com.bakss.veeam.domain.login.LoginResponse;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class VeeamBasicService {

    private String token ;
    private Integer expireAt = 0;

    @Value("${veeam.api}")
    private String openApiUrl;

    public void login() {
        String path = "/base/login";
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("123456");
        request.setCaptcha("");
        request.setCaptchaId("nH00YUnCvHz0XHx3232333");
        request.setOpenCaptcha(false);
        Response response = HttpUtils.get(openApiUrl + path, null, BeanUtils.beanToMap(request));
        LoginResponse loginResponse = BeanUtils.mapToBean(response.getData(), LoginResponse.class);
        token = loginResponse.getToken();
        expireAt = loginResponse.getExpireAt();
    }

    public void validate() {
        // todo 确认返回expireAt单位
        if (expireAt < System.currentTimeMillis()) {
            login();
        }
    }

    public List<BackupJob> getBackupJobList(Integer page, Integer pageSize) {
        validate();
        String path = "/job/getJobList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = response.getData();
        JSONArray jobList = data.getJSONArray("list");
        List<BackupJob> backupJobs = new ArrayList<>();
        if (jobList.size() > 0) {
            for (Object job: jobList) {
                BackupJob backupJob = BeanUtils.mapToBean((JSONObject)job, BackupJob.class);
                backupJobs.add(backupJob);
            }
        }
        return backupJobs;
    }

    public Response operateJob(String type, String path, String name) {
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        Response response = null;
        switch (type) {
            case "get":
                response = HttpUtils.get(openApiUrl + path, header, data);
                break;
            case "post":
                response = HttpUtils.post(openApiUrl + path, header, data);
                break;
            case "put":
                response = HttpUtils.put(openApiUrl + path, header, data);
                break;
            case "delete":
                response = HttpUtils.delete(openApiUrl + path, header, data);
                break;
        }
        return response;
    }

    public BackupJobDetail getJobDetail(String name) {
        validate();
        String path = "/job/findJob";
        Response response = operateJob("get", path, name);
        return BeanUtils.mapToBean(response.getData(), BackupJobDetail.class);
    }

    public void startJob(String name) {
        validate();
        String path = "/job/startJob";
        operateJob("put", path, name);
    }

    public void stopJob(String name) {
        validate();
        String path = "/job/stopJob";
        operateJob("put", path, name);
    }

    public void enableJob(String name) {
        validate();
        String path = "/job/enableJob";
        operateJob("put", path, name);
    }

    public void disableJob(String name) {
        validate();
        String path = "/job/disableJob";
        operateJob("put", path, name);
    }

    public void deleteJob(String name) {
        validate();
        String path = "/job/deleteJob";
        operateJob("delete", path, name);
    }

    public void createJob() {

    }
}
