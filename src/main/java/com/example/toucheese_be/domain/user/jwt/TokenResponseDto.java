package com.example.toucheese_be.domain.user.jwt;



import com.example.toucheese_be.domain.user.dto.response.UserDto;
import java.util.Date;

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
@Schema(description = "토큰 응답 DTO")
public class TokenResponseDto {
    @Schema(description = "액세스 토큰")
    private String accessToken;
    @Schema(description = "토큰 발급 시간")
    private Date issuedAt;
    @Schema(description = "토큰 만료 시간")
    private Date expiration;
    @Schema(description = "리프레시 토큰")
    private String refreshToken;
}
