package com.example.toucheese_be.global.config;

import com.example.toucheese_be.domain.user.handler.OAuth2LoginSuccessHandler;
import com.example.toucheese_be.domain.user.jwt.JwtTokenFilter;
import com.example.toucheese_be.domain.user.jwt.JwtTokenUtils;
import com.example.toucheese_be.domain.user.service.PrincipalDetailsService;
import com.example.toucheese_be.domain.user.service.PrincipalOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenUtils jwtTokenUtils;
    private final PrincipalOAuth2UserService principalOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/studio/**",
                                "/api/v1/order/**",
                                "/api/v1/toss/**",
                                "/api/v1/admin/**"
                        )
                        .authenticated()
                        .requestMatchers(
                                "/api/v1/studio/**"
                        )
                        .hasRole("MEMBER")
                        .requestMatchers(
                                "/api/v1/admin/**"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "/index.html",
                                "/static/**",
                                "/login/**",
                                "/oauth2/**"
                        )
                        .permitAll()
                )
                // JWT를 사용하기 때문에 보안 관련 세션 해제
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(principalOAuth2UserService)))
                        .successHandler(oAuth2LoginSuccessHandler))
                // 사용자가 인증되지 않은 경우 리다이렉트를 막고 대신 에러 처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("{\"error\": \"Unauthorized access\"}");
                        }))
                // JWT 필터를 권한 필터 앞에 삽입
                .addFilterBefore(
                        new JwtTokenFilter(jwtTokenUtils),
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }
}
