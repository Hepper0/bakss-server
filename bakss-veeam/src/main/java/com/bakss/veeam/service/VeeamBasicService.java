package com.bakss.veeam.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.job.BackupJob;
import com.bakss.veeam.domain.job.BackupJobDetail;
import com.bakss.veeam.domain.login.LoginRequest;
import com.bakss.veeam.domain.login.LoginResponse;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class VeeamBasicService {

    private String token ;
    private Integer expireAt = 0;

    @Value("${veeam.api}")
    private String openApiUrl;

    public void login() {
        String path = "/base/login";
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("123456");
        request.setCaptcha("");
        request.setCaptchaId("nH00YUnCvHz0XHx3232333");
        request.setOpenCaptcha(false);
        Response response = HttpUtils.get(openApiUrl + path, null, BeanUtils.beanToMap(request));
        LoginResponse loginResponse = BeanUtils.mapToBean(response.getData(), LoginResponse.class);
        token = loginResponse.getToken();
        expireAt = loginResponse.getExpireAt();
    }

    public String validate() {
        // todo 确认返回expireAt单位
        if (expireAt < System.currentTimeMillis()) {
            login();
        }
        return token;
    }


}
