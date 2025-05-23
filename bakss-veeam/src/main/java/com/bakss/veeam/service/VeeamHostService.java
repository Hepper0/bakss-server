package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.common.core.redis.RedisCache;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.host.*;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bakss.veeam.config.RedisConfig.*;

@Slf4j
@Service
public class VeeamHostService {

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
                            List<VeeamHost> hostList = getVeeamHostList(1, 100, server, false);
                            hostList.parallelStream().forEach(h -> {
                                try {
                                    getViEntityList(h.getName(),"HostAndVms", server, false);
                                } catch (Exception e) {
                                    log.error(String.format("[%s] get entity failed: %s", h.getName(), e.getMessage()));
                                }
                            });
                        } catch (Exception e) {
                            log.error(String.format("[%s] host sync failed: %s", server, e.getMessage()));
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

    private void updateCache(String server, String key, HostBase obj) {
        String redisKey = String.format("%s%s:%s", REDIS_VEEAM_HOST_PREFIX, server, key);
        List<JSONObject> cache = redisCache.getCacheList(redisKey);
        List<String> cacheKeys = cache.stream().map(c -> c.getString("ID")).collect(Collectors.toList());
        if(cacheKeys.contains(obj.getId())) {
            JSONObject existedObj = cache.stream().filter(c->obj.getId().equals(c.getString("ID"))).collect(Collectors.toList()).get(0);
            cache.remove(existedObj);
        }
        cache.add(new JSONObject(BeanUtils.beanToMap(obj)));
        redisCache.setCacheList(redisKey, cache);
    }

    public List<VeeamHost> getVeeamHostList(int page, int pageSize, String server) {
        return getVeeamHostList(page, pageSize, server, true);
    }

    public List<VeeamHost> getVeeamHostList(int page, int pageSize, String server, Boolean useCache) {
        String redisKey = String.format("%s%s:%s", REDIS_VEEAM_HOST_PREFIX, server, "host");
        if (useCache) {
            List<JSONObject> viHostRedisCache = redisCache.getCacheList(redisKey);
            if (viHostRedisCache.size() > 0) {
                List<VeeamHost> viHostList = new ArrayList<>();
                for (JSONObject obj : viHostRedisCache) {
                    viHostList.add(BeanUtils.mapToBean(obj, VeeamHost.class));
                }
                return viHostList;
            }
        }

        String token = basicService.validate(server);
        String path = "/veeamHost/getVeeamHostList";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("pageSize", pageSize);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        JSONArray hostList = data.getJSONArray("list");
        List<VeeamHost> veeamHostList = new ArrayList<>();
        if (hostList.size() > 0) {
            for (Object host: hostList) {
                VeeamHost veeamHost = BeanUtils.mapToBean((JSONObject)host, VeeamHost.class);
                veeamHostList.add(veeamHost);
//                updateCache(server, "host", veeamHost);
            }
        }
        redisCache.setCacheList(redisKey, hostList);
        redisCache.expire(redisKey, REDIS_KEY_EXPIRE);
        return veeamHostList;
    }

    public List<ViEntity> getViEntityList(String vcName, String viewMode, String server) {
        return getViEntityList(vcName, viewMode, server, true);
    }

    public List<ViEntity> getViEntityList(String vcName, String viewMode, String server, Boolean useCache) {
        String redisKey = String.format("%s%s:%s:%s", REDIS_VEEAM_HOST_PREFIX, server, "entity", vcName);
        if (useCache) {
            List<JSONObject> viEntityRedisCache = redisCache.getCacheList(redisKey);
            if (viEntityRedisCache.size() > 0) {
                List<ViEntity> viEntityList = new ArrayList<>();
                for (JSONObject obj : viEntityRedisCache) {
                    viEntityList.add(BeanUtils.mapToBean(obj, ViEntity.class));
                }
                return viEntityList;
            }
        }
        String token = basicService.validate(server);
        String path = "/veeamHost/findViEntity";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", vcName);
        query.put("viewMode", viewMode);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
//        return BeanUtils.mapToBean(data, ViEntity.class);
        JSONArray entities = data.getJSONArray("list");
        List<ViEntity> entityList = new ArrayList<>();
        if (entities.size() > 0) {
            for (Object en: entities) {
                ViEntity entity = BeanUtils.mapToBean((JSONObject)en, ViEntity.class);
                entityList.add(entity);
//                updateCache(server, "entity", entity);
            }
        }
        redisCache.setCacheList(redisKey, entities);
        redisCache.expire(redisKey, REDIS_KEY_EXPIRE);
        return entityList;
    }

    public ViDataStore getViDatastore(String serverName, String server) {
        String token = basicService.validate(server);
        String path = "/veeamHost/findViDatastore";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViDataStore.class);
    }

    public ViResourcePool getViResourcePool(String serverName, String server) {
        String token = basicService.validate(server);
        String path = "/veeamHost/findViResourcePool";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViResourcePool.class);
    }

    public ViSwitch getViSwitch(String serverName, String server) {
        String token = basicService.validate(server);
        String path = "/veeamHost/findViSwitch";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViSwitch.class);
    }

    public ViNetwork getViNetwork(String serverName, String server) {
        String token = basicService.validate(server);
        String path = "/veeamHost/findViNetwork";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViNetwork.class);
    }

    public ViFolder getViFolder(String serverName, String server) {
        String token = basicService.validate(server);
        String path = "/veeamHost/findViFolder";
        Map<String, String> header = new HashMap<>();
        header.put("x-token", token);
        Map<String, Object> query = new HashMap<>();
        query.put("serverName", serverName);
        Response response = HttpUtils.get(server + path, header, query);
        JSONObject data = (JSONObject)response.getData();
        return BeanUtils.mapToBean(data, ViFolder.class);
    }

}
