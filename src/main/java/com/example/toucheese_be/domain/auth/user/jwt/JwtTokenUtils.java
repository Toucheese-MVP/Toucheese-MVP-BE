package com.example.toucheese_be.domain.auth.user.jwt;

import com.example.toucheese_be.domain.auth.user.entity.PrincipalDetails;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenUtils {
    private final SecretKey secretKey;

    public JwtTokenUtils(
            @Value("{jwt.secret}")
            String secret
    ) {
        this.secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                SIG.HS256.key().build().getAlgorithm()
        );
    }

    // accessToken 으로 userID 획득
    public String getEmail(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("email", String.class);
    }

    // accessToken 만료 체크
    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // JWT (accessToken) 생성
    public String generateAccessToken(String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .claim("email", email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(60 * 1000)))
                .signWith(secretKey)
                .compact();
    }
}
