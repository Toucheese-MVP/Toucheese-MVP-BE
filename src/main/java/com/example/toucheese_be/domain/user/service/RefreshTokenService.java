package com.example.toucheese_be.domain.user.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final Long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 30L; // 한달

    public void saveRefreshToken(String email, String refreshToken) {
        // 이메일을 Key로, 토큰값을 value로 (opsForVale().set() -> 덮어씌우기)
        redisTemplate.opsForValue().set(email, refreshToken, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public String getRefreshToken(String email) {
        Object refreshToken = redisTemplate.opsForValue().get(email);
        return refreshToken == null ? null : (String) refreshToken;
    }

    public void deleteRefreshToken(String email) {
        redisTemplate.delete(email);
    }
}
