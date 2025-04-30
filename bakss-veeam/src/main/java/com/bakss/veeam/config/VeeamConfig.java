package com.bakss.veeam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
//@Configuration
//@ConfigurationProperties(prefix = "veeam")
//@PropertySource(value = { "classpath:config.yml" })
public class VeeamConfig {

//    @Value("${veeam.openApiUrl}")
    public static String openApiUrl = "http://192.168.1.104";

//    @Value("${veeam.username}")
    public static String username = "admin";

//    @Value("${veeam.password}")
    public static String password = "123456";

//    @Value("${veeam.captchaId}")
    public static String captchaId = "nH00YUnCvHz0XHx3232333";
}
