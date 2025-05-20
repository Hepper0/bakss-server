package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.core.redis.RedisCache;
import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.VeeamToken;
import com.bakss.veeam.domain.backup.Backup;
import com.bakss.veeam.domain.host.ViEntity;
import com.bakss.veeam.domain.job.ApplyBackupJob;
import com.bakss.veeam.domain.job.BackupJob;
import com.bakss.veeam.domain.job.BackupJobDetail;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bakss.veeam.config.RedisConfig.REDIS_VEEAM_HOST_PREFIX;


@Service
public class VeeamJobService {

    @Resource
    VeeamBasicService basicService;

    @Resource
    RedisCache redisCache;

    private final Long REFRESH_INTERVAL = 1000 * 60 * 10L;

    private final Integer REDIS_KEY_EXPIRE = 60;

    private final String REDIS_BACKUP_JOB_KEY = "backupJob";

//    private final String openApiUrl = VeeamConfig.openApiUrl;

//    private String token;

    private final String[] backupServers = new String[]{"http://192.168.1.104:8888/"};

//    @PostConstruct
    public void syncBackupData() {

        new Thread(() -> {
            try {
                Thread.sleep(REFRESH_INTERVAL);
            } catch (Exception ignored){}

            try {
                refreshBackupCache();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void refreshBackupCache() {
        for(String server: backupServers) {
            String redisKey = String.format("%s%s:%s", REDIS_VEEAM_HOST_PREFIX, server, REDIS_BACKUP_JOB_KEY);
            List<BackupJob> backupList = getBackupJobList(null,1, 100, server);
            redisCache.setCacheList(redisKey, backupList.stream().map(BeanUtils::beanToMap).collect(Collectors.toList()));
            // todo 同步backup表的记录
        }

    }

    public List<BackupJob> getBackupJobList(String jobName, Integer page, Integer pageSize, String server) {
        List<BackupJob> backupJobs = new ArrayList<>();
        String redisKey = String.format("%s%s:%s", REDIS_VEEAM_HOST_PREFIX, server, REDIS_BACKUP_JOB_KEY);
        List<JSONObject> backupJobRedisCache = redisCache.getCacheList(redisKey);
        if (backupJobRedisCache.size() > 0) {
            if (jobName != null) {
                backupJobRedisCache = backupJobRedisCache.stream().filter(b -> jobName.equals(b.getString("name"))).collect(Collectors.toList());
            }
            backupJobRedisCache.forEach(j -> {
                backupJobs.add(BeanUtils.mapToBean(j, BackupJob.class));
            });
            if (pageSize == 0) return backupJobs;
            int pageStart = (page - 1) * page;
            int pageEnd = page * pageSize;
            if (backupJobs.size() < pageSize * page) {
                pageEnd = backupJobs.size();
            }
            return backupJobs.subList(pageStart, pageEnd);
        }
        String token = basicService.validate(server);
        String path = "/job/getJobList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("jobName", jobName);
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray jobList = data.getJSONArray("list");

        if (jobList.size() > 0) {
            for (Object job: jobList) {
                BackupJob backupJob = BeanUtils.mapToBean((JSONObject)job, BackupJob.class);
                backupJobs.add(backupJob);
            }
        }
        redisCache.setCacheList(redisKey, jobList);
        redisCache.expire(redisKey, REDIS_KEY_EXPIRE);
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

    public void createJob(ApplyBackupJob applyBackupJob, String server) {
        String name = applyBackupJob.getName();
        String description = applyBackupJob.getDescription();
        String repository = applyBackupJob.getRepository();
        String afterJobName = applyBackupJob.getAfterJobName();
        String token = basicService.validate(server);
        String path = "/job/createJob";
        BackupJobDetail jobDetail = new BackupJobDetail();
        jobDetail.setName(name);
        jobDetail.setDescription(description);
        jobDetail.setSelectedVmObjects(applyBackupJob.getVmObjects());

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
        schedule.put("isScheduleEnabled", applyBackupJob.getIsScheduleEnable());
        schedule.put("policy", applyBackupJob.getPolicy());
//        JSONObject optionsDaily = new JSONObject();
//        optionsDaily.put("startDateTimeLocal", "00:00");
//        optionsDaily.put("dayNumberInMonth", "Weekdays");
//        optionsDaily.put("dayOfWeek", new JSONArray());
        Map<String, Object> dailyOptions = BeanUtils.beanToMap(applyBackupJob.getScheduleDaily());
        if (dailyOptions != null)
            schedule.put("optionsDaily", new JSONObject(dailyOptions));

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
        HttpUtils.post(server + path, header, new JSONObject(BeanUtils.beanToMap(jobDetail)));
    }

    public void updateJob(BackupJobDetail jobDetail, String server) {
        String token = basicService.validate(server);
        String path = "/job/updateJob";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        HttpUtils.put(server + path, header, new JSONObject(BeanUtils.beanToMap(jobDetail)));
    }
}
