package com.bakss.veeam.domain.login;


import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Long expiresAt;
}
