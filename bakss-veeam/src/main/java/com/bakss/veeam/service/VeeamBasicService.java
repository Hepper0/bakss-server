package com.bakss.veeam.service;

import com.bakss.veeam.config.VeeamConfig;
import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.login.LoginRequest;
import com.bakss.veeam.domain.login.LoginResponse;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;


@Service
public class VeeamBasicService {

    private String token ;
    private Integer expireAt = 0;

    public void login() {
        String path = "/base/login";
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("123456");
        request.setCaptcha("");
        request.setCaptchaId("nH00YUnCvHz0XHx3232333");
        request.setOpenCaptcha(false);
        Response response = HttpUtils.get(VeeamConfig.openApiUrl + path, null, BeanUtils.beanToMap(request));
        assert response != null : "请求返回为空";
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
