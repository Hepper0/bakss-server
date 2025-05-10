package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.VeeamToken;
import com.bakss.veeam.domain.repository.VeeamRepository;
import com.bakss.veeam.domain.repository.VeeamRepositoryDetail;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VeeamRepositoryService {
    @Resource
    VeeamBasicService basicService;

//    private final String openApiUrl = VeeamConfig.openApiUrl;

//    private String token;

    public List<VeeamRepository> getVeeamRepositoryList(int page, int pageSize, String server) {
        String token = basicService.validate(server);
        String path = "/veeamrepository/getVeeamRepositoryList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray repositoryList = data.getJSONArray("list");
        List<VeeamRepository> VeeamRepositoryList = new ArrayList<>();
        if (repositoryList.size() > 0) {
            for (Object repository: repositoryList) {
                VeeamRepository veeamRepository = BeanUtils.mapToBean((JSONObject)repository, VeeamRepository.class);
                VeeamRepositoryList.add(veeamRepository);
            }
        }
        return VeeamRepositoryList;
    }

    public VeeamRepositoryDetail getVeeamRepositoryDetail(String ID, String server) {
        String token = basicService.validate(server);
        String path = "/veeamrepository/findVeeamRepository";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("ID", ID);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, VeeamRepositoryDetail.class);
    }

    public void deleteVeeamRepository(String name, String server) {
        String token = basicService.validate(server);
        String path = "/veeamrepository/deleteVeeamRepository";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("name", name);
        HttpUtils.delete(server + path, header, query);
    }
}
