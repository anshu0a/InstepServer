package com.instep.dao.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private UserResponse user;

    private String accessToken;
    private String refreshToken;

    private String tokenType;
    private Long expiresIn;

    private boolean success;
    private String message;
}
