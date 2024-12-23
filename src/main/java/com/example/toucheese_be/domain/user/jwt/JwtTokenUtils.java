package com.example.toucheese_be.domain.user.jwt;

import com.example.toucheese_be.domain.user.entity.PrincipalDetails;
import com.example.toucheese_be.global.common.constant.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenUtils {
    private final SecretKey secretKey;
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 30L;

    public JwtTokenUtils(
            @Value("${jwt.secret}")
            String secret
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }


    // JWT (accessToken) 생성
    public String generateAccessToken(PrincipalDetails principalDetails) {
        Date now = new Date();
        return Jwts.builder()
                .subject(principalDetails.getUsername())
                .claim("userId", principalDetails.getUserId())
                .claim("email", principalDetails.getEmail())
                .claim("auth", principalDetails.getAuthorities().toString())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }


    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        PrincipalDetails principalDetails = PrincipalDetails.builder()
                .userId(claims.get("userId", Long.class))
                .username(claims.getSubject())
                .email(claims.get("email", String.class))
                .authorities(claims.get("auth", String.class))
                .build();
        return new UsernamePasswordAuthenticationToken(principalDetails, "", principalDetails.getAuthorities());
    }


    // 정상적인 JWT 인지 판단
    public boolean validate(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            if (e instanceof SecurityException) {
                log.debug("[SecurityException] 잘못된 토큰");
                throw new GlobalCustomException(ErrorCode.JWT_SECURITY_EXCEPTION, e);
            } else if (e instanceof MalformedJwtException) {
                log.debug("[MalformedJwtException] 잘못된 토큰");
                throw new GlobalCustomException(ErrorCode.MALFORMED_JWT_EXCEPTION, e);
            } else if (e instanceof ExpiredJwtException) {
                log.debug("[ExpiredJwtException] 토큰 만료");
                throw new GlobalCustomException(ErrorCode.EXPIRED_JWT_EXCEPTION, e);
            } else if (e instanceof UnsupportedJwtException) {
                log.debug("[UnsupportedJwtException] 잘못된 형식의 토큰");
                throw new GlobalCustomException(ErrorCode.UNSUPPORTED_JWT_EXCEPTION, e);
            } else if (e instanceof IllegalArgumentException) {
                log.debug("[IllegalArgumentException]");
                throw new GlobalCustomException(ErrorCode.ILLEGAL_ARGUMENT_JWT_EXCEPTION, e);
            } else {
                log.debug("[토큰검증 오류]" + e.getClass());
                throw new GlobalCustomException(ErrorCode.TOKEN_VALIDATION_FAIL, e);
            }
        }
    }

    // JWT
    public Claims getClaims(String token) {
        // 형식, 서명, 만료 시간 검증 통과
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public Long extractUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    public String extractEmailFromToken(String token) {
        return getClaims(token).get("email", String.class);
    }
}
