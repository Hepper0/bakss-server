package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.core.redis.RedisCache;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.ResponseList;
import com.bakss.veeam.domain.backup.*;
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

import static com.bakss.veeam.config.RedisConfig.*;

@Service
public class VeeamBackupService {

    @Resource
    VeeamBasicService basicService;

    @Resource
    RedisCache redisCache;

    private final Long REFRESH_INTERVAL = 1000 * 60 * 10L;

//    private String openApiUrl = VeeamConfig.openApiUrl;

//    private String token;
    private final String[] backupServers = new String[]{"192.168.1.104"};

    @PostConstruct
    public void syncBackupData() {

        new Thread(() -> {
            try {
                refreshBackupCache();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(REFRESH_INTERVAL);
            } catch (Exception ignored){}

        }).start();
    }

    public void refreshBackupCache() {
        for(String server: backupServers) {
            String redisKey = String.format("%s%s:%s", REDIS_VEEAM_HOST_PREFIX, server, "backupJob");
            List<Backup> backupList = getBackupList(1, 100, server);
            redisCache.setCacheList(redisKey, backupList.stream().map(BeanUtils::beanToMap).collect(Collectors.toList()));
            // todo 同步backup表的记录
        }

    }

    public List<Backup> getBackupList(int page, int pageSize, String server) {
        String token = basicService.validate(server);
        String path = "/backup/getBackupList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray backups = data.getJSONArray("list");
        List<Backup> backupList = new ArrayList<>();
        if (backups.size() > 0) {
            for (Object bk: backups) {
                backupList.add(BeanUtils.mapToBean((JSONObject)bk, Backup.class));
            }
        }
        return backupList;
    }

    public List<ChildBackup> getChildBackupList(int parentId, String server) {
        String token = basicService.validate(server);
        String path = "/backup/getChildBackupList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("id", parentId);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray backups = data.getJSONArray("list");
        List<ChildBackup> childBackupList = new ArrayList<>();
        if (backups.size() > 0) {
            for (Object bk: backups) {
                childBackupList.add(BeanUtils.mapToBean((JSONObject)bk, ChildBackup.class));
            }
        }
        return childBackupList;
    }

    public List<Application> getApplicationByName(String[] names, String server) {
        String token = basicService.validate(server);
        String path = "/backup/getApplicationByNames";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("Names", names);
        ResponseList response = (ResponseList)HttpUtils.get(server + path, header, query, true);
        JSONArray data = response.getData();
        List<Application> applications = new ArrayList<>();
        for(Object app : data) {
            applications.add(BeanUtils.mapToBean((JSONObject)app, Application.class));
        }
        return applications;
    }

    public Backup getBackupDetail(String ID, String server) {
        String token = basicService.validate(server);
        String path = "/backup/findBackup";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("ID", ID);
        Response response = HttpUtils.get(server + path, header, query);
        return BeanUtils.mapToBean(response.getData(), Backup.class);
    }

    public VMPointDetail getVMPointDetail(String ID, String backupId, String server) {
        String token = basicService.validate(server);
        String path = "/backup/getVMPointDetail";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("id", ID);
        query.put("backupId", backupId);
        Response response = HttpUtils.get(server + path, header, query);
        return BeanUtils.mapToBean(response.getData(), VMPointDetail.class);
    }

    public List<VMPoint> getVMPoints(String backupId, String vmName, String server) {
        String token = basicService.validate(server);
        String path = "/backup/getVMPoints";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("backupId", backupId);
        query.put("vmName", vmName);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray points = data.getJSONArray("list");
        List<VMPoint> vmPointList = new ArrayList<>();
        if (points.size() > 0) {
            for (Object point: points) {
                vmPointList.add(BeanUtils.mapToBean((JSONObject)point, VMPoint.class));
            }
        }
        return vmPointList;
    }

}
