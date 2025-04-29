package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.proxy.BackupProxy;
import com.bakss.veeam.domain.repository.VeeamRepository;
import com.bakss.veeam.domain.repository.VeeamRepositoryDetail;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${veeam.api}")
    private String openApiUrl;

    private String token;

    public List<VeeamRepository> getVeeamRepositoryList(int page, int pageSize) {
        token = basicService.validate();
        String path = "/veeamrepository/getVeeamRepositoryList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = response.getData();
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

    public VeeamRepositoryDetail getVeeamRepositoryDetail(String ID) {
        token = basicService.validate();
        String path = "/veeamrepository/findVeeamRepository";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("ID", ID);
        Response response = HttpUtils.get(openApiUrl + path, header, query);
        JSONObject data = response.getData();
        return BeanUtils.mapToBean(data, VeeamRepositoryDetail.class);
    }

    public void deleteVeeamRepository(String name) {
        token = basicService.validate();
        String path = "/veeamrepository/deleteVeeamRepository";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("name", name);
        HttpUtils.delete(openApiUrl + path, header, query);
    }
}
