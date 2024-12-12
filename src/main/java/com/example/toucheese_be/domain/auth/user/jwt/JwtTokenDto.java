package com.example.toucheese_be.domain.auth.user.jwt;

import java.time.LocalDateTime;
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
public class JwtTokenDto {
    // private Long userId;
    private String accessToken;
    // private LocalDateTime expiredDate;
    // private Long expiredSecond;
}
