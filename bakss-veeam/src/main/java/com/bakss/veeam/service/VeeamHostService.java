package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.host.*;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class VeeamHostService {

    @Resource
    VeeamBasicService basicService;

    private final String openApiUrl = VeeamConfig.openApiUrl;

    private String token;

    public List<VeeamHost> getVeeamHostList(int page, int pageSize) {
        token = basicService.validate();
        String path = "/veeamHost/getVeeamHostList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray hostList = data.getJSONArray("list");
        List<VeeamHost> veeamHostList = new ArrayList<>();
        if (hostList.size() > 0) {
            for (Object host: hostList) {
                VeeamHost veeamHost = BeanUtils.mapToBean((JSONObject)host, VeeamHost.class);
                veeamHostList.add(veeamHost);
            }
        }
        return veeamHostList;
    }

    public ViEntity getViEntity(String serverName, String viewMode) {
        token = basicService.validate();
        String path = "/veeamHost/findViEntity";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        query.put("viewMode", viewMode);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViEntity.class);
    }

    public ViDataStore getViDatastore(String serverName) {
        token = basicService.validate();
        String path = "/veeamHost/findViDatastore";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViDataStore.class);
    }

    public ViResourcePool getViResourcePool(String serverName) {
        token = basicService.validate();
        String path = "/veeamHost/findViResourcePool";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViResourcePool.class);
    }

    public ViSwitch getViSwitch(String serverName) {
        token = basicService.validate();
        String path = "/veeamHost/findViSwitch";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViSwitch.class);
    }

    public ViNetwork getViNetwork(String serverName) {
        token = basicService.validate();
        String path = "/veeamHost/findViNetwork";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViNetwork.class);
    }

    public ViFolder getViFolder(String serverName) {
        token = basicService.validate();
        String path = "/veeamHost/findViFolder";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViFolder.class);
    }

}
