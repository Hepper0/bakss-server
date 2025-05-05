package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.proxy.BackupProxy;
import com.bakss.veeam.domain.proxy.BackupProxyDetail;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VeeamProxyService {

    @Resource
    VeeamBasicService basicService;

    private final String openApiUrl = VeeamConfig.openApiUrl;

    private String token;

    public List<BackupProxy> getBackupProxyList(int page, int pageSize) {
        token = basicService.validate();
        String path = "/backupproxy/getBackupProxyList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        assert response != null : "请求返回为空";
        JSONObject data = (JSONObject)response.getData();
        JSONArray proxyList = data.getJSONArray("list");
        List<BackupProxy> backupProxyList = new ArrayList<>();
        if (proxyList.size() > 0) {
            for (Object proxy: proxyList) {
                BackupProxy backupProxy = BeanUtils.mapToBean((JSONObject)proxy, BackupProxy.class);
                backupProxyList.add(backupProxy);
            }
        }
        return backupProxyList;
    }

    public BackupProxyDetail getBackupProxyDetail(String ID) {
        token = basicService.validate();
        String path = "/backupproxy/findBackupProxy";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("ID", ID);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, BackupProxyDetail.class);
    }

    public void deleteBackupProxy(String name) {
        token = basicService.validate();
        String path = "/backupproxy/deleteBackupProxy";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("name", name);
        HttpUtils.delete(openApiUrl + path, header, query);
    }

}
