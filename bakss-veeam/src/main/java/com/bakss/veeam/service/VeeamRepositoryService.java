package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.core.redis.RedisCache;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.repository.VeeamRepository;
import com.bakss.veeam.domain.repository.VeeamRepositoryDetail;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bakss.veeam.config.RedisConfig.*;

@Slf4j
@Service
public class VeeamRepositoryService {
    @Resource
    VeeamBasicService basicService;

    @Resource
    RedisCache redisCache;

    private final Long REFRESH_INTERVAL = 1000 * 60 * 60 * 8L;

    public void refreshCache(List<String> backupServers) {
        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000L);
            } catch (Exception ignored){}

            while (true) {
                try {
                    for(String server: backupServers) {
                        try {
                            getVeeamRepositoryList(1, 100, server, false);
                        } catch (Exception e) {
                            log.error(String.format("[%s] repository sync failed: %s", server, e.getMessage()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(REFRESH_INTERVAL);
                } catch (Exception ignored){}
            }
        }).start();
    }

    public List<VeeamRepository> getVeeamRepositoryList(int page, int pageSize, String server) {
        return getVeeamRepositoryList(page, pageSize, server, true);
    }

    public List<VeeamRepository> getVeeamRepositoryList(int page, int pageSize, String server, Boolean useCache) {
        String redisKey = String.format("%s%s:%s", REDIS_VEEAM_HOST_PREFIX, server, "repository");
        if (useCache) {
            List<JSONObject> repositoryRedisCache = redisCache.getCacheList(redisKey);
            if (repositoryRedisCache.size() > 0) {
                List<VeeamRepository> viEntityList = new ArrayList<>();
                for (JSONObject obj : repositoryRedisCache) {
                    viEntityList.add(BeanUtils.mapToBean(obj, VeeamRepository.class));
                }
                return viEntityList;
            }
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
