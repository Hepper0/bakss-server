package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.VeeamToken;
import com.bakss.veeam.domain.host.ViEntity;
import com.bakss.veeam.domain.job.BackupJob;
import com.bakss.veeam.domain.job.BackupJobDetail;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class VeeamJobService {

    @Resource
    VeeamBasicService basicService;

//    private final String openApiUrl = VeeamConfig.openApiUrl;

//    private String token;

    public List<BackupJob> getBackupJobList(Integer page, Integer pageSize, String server) {
        String token = basicService.validate(server);
        String path = "/job/getJobList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
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

    public Response operateJob(String type, String path, String name, String server) {
        String token = basicService.validate(server);
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        Response response = null;
        switch (type) {
            case "get":
                response = HttpUtils.get(server + path, header, data);
                break;
            case "post":
                response = HttpUtils.post(server + path, header, data);
                break;
            case "put":
                response = HttpUtils.put(server + path, header, data);
                break;
            case "delete":
                response = HttpUtils.delete(server + path, header, data);
                break;
        }
        return response;
    }

    public BackupJobDetail getJobDetail(String name, String server) {
        String path = "/job/findJob";
        Response response = operateJob("get", path, name, server);
        return BeanUtils.mapToBean(response.getData(), BackupJobDetail.class);
    }

    public void startJob(String name, String server) {
        String path = "/job/startJob";
        operateJob("put", path, name, server);
    }

    public void stopJob(String name, String server) {
        String path = "/job/stopJob";
        operateJob("put", path, name, server);
    }

    public void enableJob(String name, String server) {
        String path = "/job/enableJob";
        operateJob("put", path, name, server);
    }

    public void disableJob(String name, String server) {
        String path = "/job/disableJob";
        operateJob("put", path, name, server);
    }

    public void deleteJob(String name, String server) {
        String path = "/job/deleteJob";
        operateJob("delete", path, name, server);
    }

    public void createJob(String name, String description, List<ViEntity> vmObjects, String repository, String afterJobName, String server) {
        String token = basicService.validate(server);
        String path = "/job/createJob";
        BackupJobDetail jobDetail = new BackupJobDetail();
        jobDetail.setName(name);
        jobDetail.setDescription(description);
        jobDetail.setSelectedVmObjects(vmObjects);

        JSONObject backupProxyOptions = new JSONObject();
        backupProxyOptions.put("policyType", "Auto");
        backupProxyOptions.put("selectedProxys", new ArrayList<>());
        jobDetail.setBackupProxyOptions(backupProxyOptions);

        JSONObject jobOptions = new JSONObject();
        jobOptions.put("retainCycles", 7);
        jobOptions.put("retentionPolicy", "Days");
        jobDetail.setJobOptions(jobOptions);

        jobDetail.setKeepFullBackupEnabled(false);
        jobDetail.setFullBackupGfsConfig(new JSONObject());
        jobDetail.setBackupRepositoryName(repository);
        jobDetail.setAdvancedConfig(null);

        JSONObject guestInteractionProxy = new JSONObject();
        guestInteractionProxy.put("policyType", "Auto");
        guestInteractionProxy.put("selectedGuestProxys", new ArrayList<>());
        jobDetail.setGuestInteractionProxy(guestInteractionProxy);

        jobDetail.setDefaultGuestOSCred("");
        jobDetail.setIndividualCreds(new ArrayList<>());
        jobDetail.setApplicationAwareOptions(new ArrayList<>());

        JSONObject schedule = new JSONObject();
        schedule.put("isScheduleEnabled", true);
        schedule.put("policy", "Daily");
        JSONObject optionsDaily = new JSONObject();
        optionsDaily.put("startDateTimeLocal", "00:00");
        optionsDaily.put("dayNumberInMonth", "Weekdays");
        optionsDaily.put("dayOfWeek", new JSONArray());
        schedule.put("optionsDaily", optionsDaily);

        JSONObject optionsMonthly = new JSONObject();
        optionsMonthly.put("time", "00:00");

        JSONObject dayInMonth = new JSONObject();
        dayInMonth.put("dayNumberInMonth", "First");
        dayInMonth.put("dayOfWeek", "Monday");
        dayInMonth.put("dayOfMonth", "");
        optionsMonthly.put("dayInMonth", dayInMonth);

        optionsMonthly.put("months", new JSONArray());
        schedule.put("optionsMonthly", optionsMonthly);

        JSONObject optionsPeriodically = new JSONObject();
        optionsPeriodically.put("periodNum", 1);
        optionsPeriodically.put("periodUnit", "Hours");

        JSONObject optionsSchedule = new JSONObject();
        JSONObject scheduleDetail = new JSONObject();
        scheduleDetail.put("checked", true);
        scheduleDetail.put("startTime", "00:00");
        scheduleDetail.put("endTime", "23:00");
        optionsSchedule.put("Monday", scheduleDetail);
        optionsSchedule.put("Tuesday", scheduleDetail);
        optionsSchedule.put("Wednesday", scheduleDetail);
        optionsSchedule.put("Thursday", scheduleDetail);
        optionsSchedule.put("Friday", scheduleDetail);
        optionsSchedule.put("Saturday", scheduleDetail);
        optionsSchedule.put("Sunday", scheduleDetail);
        optionsPeriodically.put("schedule", optionsSchedule);

        optionsPeriodically.put("hourlyOffset", 0);
        schedule.put("optionsPeriodically", optionsPeriodically);

        JSONObject optionsContinuous = new JSONObject();
        optionsContinuous.put("afterJobName", afterJobName);
        schedule.put("optionsContinuous", optionsContinuous);

        jobDetail.setSchedule(schedule);
        jobDetail.setRetrySpecified(false);
        jobDetail.setRetryTimes(3);
        jobDetail.setRetryTimeout(10);
        JSONObject terminateOutWindow = new JSONObject();
        terminateOutWindow.put("schedule", optionsSchedule); // reused
        jobDetail.setTerminateOutWindow(terminateOutWindow);
        jobDetail.setTerminateOutWindowEnabled(false);

        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        HttpUtils.post(server + path, header, BeanUtils.beanToMap(jobDetail));
    }

    public void updateJob(BackupJobDetail jobDetail, String server) {
        String token = basicService.validate(server);
        String path = "/job/updateJob";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        HttpUtils.put(server + path, header, BeanUtils.beanToMap(jobDetail));
    }
}
