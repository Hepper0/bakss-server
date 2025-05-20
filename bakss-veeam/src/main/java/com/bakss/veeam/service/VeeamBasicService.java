package com.bakss.veeam.service;

import com.bakss.veeam.domain.Response;
import com.bakss.veeam.domain.login.LoginRequest;
import com.bakss.veeam.domain.login.LoginResponse;
import com.bakss.veeam.utils.BeanUtils;
import com.bakss.veeam.utils.HttpUtils;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;


@Service
public class VeeamBasicService {

    private final Map<String, String> token = new HashMap<>();
    private final Map<String, Long> expiresAt = new HashMap<>();

    public void login(String server) {
        String path = "/base/login";
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("123456");
        request.setCaptcha("");
        request.setCaptchaId("nH00YUnCvHz0XHx3232333");
        request.setOpenCaptcha(false);
        Response response = HttpUtils.post(server + path, null, BeanUtils.beanToMap(request));
        LoginResponse loginResponse = BeanUtils.mapToBean(response.getData(), LoginResponse.class);
        token.put(server, loginResponse.getToken());
        expiresAt.put(server, loginResponse.getExpiresAt());
    }

    public String validate(String server) {
        if (expiresAt.get(server) == null || expiresAt.get(server) <= System.currentTimeMillis()) {
            login(server);
        }
//        VeeamToken veeamToken = new VeeamToken();
//        if (!server.startsWith("http://")) {
//            server = "http://" + server;
//        }
//        veeamToken.setToken(token.get(server));
//        veeamToken.setServer(server);
        return token.get(server);
    }

//    public VeeamToken validate() {
//        String server;
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        if (requestAttributes != null) {
//            HttpServletRequest request = requestAttributes.getRequest();
//            server = request.getHeader("server");
//        } else {
//            throw new RuntimeException("The header is missing the server field");
//        }
//
//        return validate(server);
//    }

}
