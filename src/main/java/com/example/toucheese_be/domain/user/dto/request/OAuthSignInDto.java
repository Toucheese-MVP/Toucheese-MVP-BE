package com.example.toucheese_be.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OAuthSignInDto {
    private String socialId;
    private String email;
    private String username;
    private String socialProvider;
}



