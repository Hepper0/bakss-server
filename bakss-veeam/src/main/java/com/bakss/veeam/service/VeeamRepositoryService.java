package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.core.redis.RedisCache;
import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.VeeamToken;
import com.bakss.veeam.domain.host.ViEntity;
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

import static com.bakss.veeam.config.RedisConfig.REDIS_VEEAM_HOST_PREFIX;

@Service
public class VeeamRepositoryService {
    @Resource
    VeeamBasicService basicService;

    @Resource
    RedisCache redisCache;

    private final Integer REDIS_KEY_EXPIRE = 12 * 60 * 60;

//    private final String openApiUrl = VeeamConfig.openApiUrl;

//    private String token;

    public List<VeeamRepository> getVeeamRepositoryList(int page, int pageSize, String server) {
        String redisKey = String.format("%s%s:%s", REDIS_VEEAM_HOST_PREFIX, server, "repository");
        List<JSONObject> repositoryRedisCache = redisCache.getCacheList(redisKey);
        if (repositoryRedisCache.size() > 0) {
            List<VeeamRepository> viEntityList = new ArrayList<>();
            for (JSONObject obj : repositoryRedisCache) {
                viEntityList.add(BeanUtils.mapToBean(obj, VeeamRepository.class));
            }
            return viEntityList;
        }
        String token = basicService.validate(server);
        String path = "/veeamRepository/getVeeamRepositoryList";
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
        redisCache.setCacheList(redisKey, repositoryList);
        redisCache.expire(redisKey, REDIS_KEY_EXPIRE);
        return VeeamRepositoryList;
    }

    public VeeamRepositoryDetail getVeeamRepositoryDetail(String ID, String server) {
        String token = basicService.validate(server);
        String path = "/veeamRepository/findVeeamRepository";
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
        String path = "/veeamRepository/deleteVeeamRepository";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("name", name);
        HttpUtils.delete(server + path, header, query);
    }
}
