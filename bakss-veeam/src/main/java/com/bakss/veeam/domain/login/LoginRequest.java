package com.bakss.veeam.domain.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String captcha;
    private String captchaId;
    private Boolean openCaptcha;
}
