package com.example.toucheese_be.domain.user.handler;

import com.example.toucheese_be.domain.user.entity.PrincipalDetails;
import com.example.toucheese_be.domain.user.jwt.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        log.info("OAuth2 Login 성공");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        log.info("Token 생성 시작");
        String accessToken = jwtTokenUtils.generateAccessToken(principalDetails);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"accessToken\": \"" + accessToken + "\"}");
        log.info("발급된 accessToken: {}", accessToken);
    }
}
