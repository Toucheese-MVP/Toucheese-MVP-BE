package com.example.toucheese_be.domain.auth.user.jwt;


import com.example.toucheese_be.domain.auth.user.entity.PrincipalDetails;
import com.example.toucheese_be.domain.auth.user.service.PrincipalDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;
    private final PrincipalDetailsService principalDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {
                "/api/v1/studio/**",
                "/api/v1/order/**",
                "/api/v1/auth/**"
                //"/api/v1/admin/**"
        };
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authHeader.split(" ")[1];
        // 토큰 소멸 시간 검증
        if (jwtTokenUtils.isExpired(accessToken)) {
            log.info("accessToken 이 만료 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }
        String email = jwtTokenUtils.getEmail(accessToken);
        PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(email);
        AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                principalDetails, accessToken, principalDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        log.info("현재 사용자 : {}", SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("이메일 : {}", email);
        log.info("권한 : {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        // 다음 필터 실행
        filterChain.doFilter(request, response);
    }
}


