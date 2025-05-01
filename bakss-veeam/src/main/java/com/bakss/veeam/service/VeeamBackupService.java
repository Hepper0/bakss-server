package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.backup.Backup;
import com.bakss.veeam.domain.backup.ChildBackup;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VeeamBackupService {

    @Resource
    VeeamBasicService basicService;

    private final String openApiUrl = VeeamConfig.openApiUrl;

    private String token;

    public List<Backup> getBackupList(int page, int pageSize) {
        token = basicService.validate();
        String path = "/backup/getBackupList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = response.getData();
        JSONArray backups = data.getJSONArray("list");
        List<Backup> backupList = new ArrayList<>();
        if (backups.size() > 0) {
            for (Object bk: backups) {
                backupList.add(BeanUtils.mapToBean((JSONObject)bk, Backup.class));
            }
        }
        return backupList;
    }

    public List<ChildBackup> getChildBackupList(int parentId) {
        token = basicService.validate();
        String path = "/backup/getChildBackupList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("id", parentId);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = response.getData();
        JSONArray backups = data.getJSONArray("list");
        List<ChildBackup> childBackupList = new ArrayList<>();
        if (backups.size() > 0) {
            for (Object bk: backups) {
                childBackupList.add(BeanUtils.mapToBean((JSONObject)bk, ChildBackup.class));
            }
        }
        return childBackupList;
    }

}
