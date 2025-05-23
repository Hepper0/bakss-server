package com.bakss.veeam.config;

public interface RedisConfig {

    String REDIS_VEEAM_HOST_PREFIX = "veeam:host:";
    Long REDIS_KEY_EXPIRE = 12 * 60 * 60L;
}
