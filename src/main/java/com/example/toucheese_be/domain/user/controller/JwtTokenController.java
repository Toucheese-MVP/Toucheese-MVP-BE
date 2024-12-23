package com.example.toucheese_be.domain.user.controller;

import com.example.toucheese_be.domain.user.jwt.TokenRequestDto;
import com.example.toucheese_be.domain.user.jwt.TokenResponseDto;
import com.example.toucheese_be.domain.user.jwt.JwtTokenUtils;
import com.example.toucheese_be.domain.user.service.PrincipalDetailsService;
import com.example.toucheese_be.domain.user.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class JwtTokenController {
    private final PrincipalDetailsService principalDetailsService;

    @PostMapping("/reissuance")
    public ResponseEntity<TokenResponseDto> refreshAccessToken(
        @RequestBody
        TokenRequestDto dto
    ) {
        return ResponseEntity.ok(principalDetailsService.refreshAccessToken(dto));
    }

}
