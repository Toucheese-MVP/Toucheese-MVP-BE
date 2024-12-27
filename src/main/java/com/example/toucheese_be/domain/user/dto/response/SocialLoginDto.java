package com.example.toucheese_be.domain.user.dto.response;

import com.example.toucheese_be.domain.user.constant.Role;
import com.example.toucheese_be.domain.user.constant.SocialProvider;
import com.example.toucheese_be.domain.user.jwt.TokenResponseDto;
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
public class SocialLoginDto {
    private Long userId;
    private String email;
    private String username;
    private String nickname;
    private String phone;
    private String profileImg;
    private Role role;
    private SocialProvider socialProvider;
    private TokenResponseDto tokens;
}
