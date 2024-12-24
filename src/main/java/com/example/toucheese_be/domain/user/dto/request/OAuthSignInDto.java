package com.example.toucheese_be.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "소셜 로그인 요청 DTO")
public class OAuthSignInDto {
    @Schema(description = "socialId", example = "9999")
    private String socialId;
    @Schema(description = "이메일", example = "example@ngmail.com")
    private String email;
    @Schema(description = "사용자 이름", example = "홍길동")
    private String username;
    @Schema(description = "소셜 provider (naver, google, apple 등)", example = "apple")
    private String socialProvider;
}



