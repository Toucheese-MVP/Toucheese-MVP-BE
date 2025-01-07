package com.example.toucheese_be.domain.user.constant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "소셜 로그인 제공자")
public enum SocialProvider {
    @Schema(description = "네이버")
    naver,
    @Schema(description = "구글")
    google,
    @Schema(description = "카카오")
    kakao,
    @Schema(description = "애플")
    apple
}
