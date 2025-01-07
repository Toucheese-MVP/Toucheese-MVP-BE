package com.example.toucheese_be.domain.user.dto.response;

import com.example.toucheese_be.domain.user.constant.Role;
import com.example.toucheese_be.domain.user.constant.SocialProvider;
import com.example.toucheese_be.domain.user.jwt.TokenResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "소셜 로그인 응답 DTO")
public class SocialLoginDto {
    @Schema(description = "유저 ID")
    private Long userId;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "유저 이름")
    private String username;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "휴대폰 번호")
    private String phone;
    @Schema(description = "프로필 이미지")
    private String profileImg;
    @Schema(description = "유저 권한", implementation = Role.class)
    private Role role;
    @Schema(description = "소셜 로그인 제공자", implementation = SocialProvider.class)
    private SocialProvider socialProvider;
    @Schema(description = "토큰 정보", implementation = TokenResponseDto.class)
    private TokenResponseDto tokens;
}
