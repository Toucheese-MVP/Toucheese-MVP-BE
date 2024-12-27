package com.example.toucheese_be.domain.user.controller;

import com.example.toucheese_be.domain.user.dto.response.SocialLoginDto;
import com.example.toucheese_be.domain.user.jwt.TokenRequestDto;
import com.example.toucheese_be.domain.user.jwt.TokenResponseDto;
import com.example.toucheese_be.domain.user.jwt.JwtTokenUtils;
import com.example.toucheese_be.domain.user.service.PrincipalDetailsService;
import com.example.toucheese_be.domain.user.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "액세스 토큰 재발급",
            description = "<p>클라이언트가 가지고 있는 액세스 토큰과 리프레시 토큰으로 액세스 토큰을 재발급합니다.</p>"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공 응답",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "4016", description = "리프레시 토큰 불일치",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "4020", description = "액세스 토큰이 만료되었습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<SocialLoginDto> refreshAccessToken(
        @RequestBody
        TokenRequestDto dto
    ) {
        return ResponseEntity.ok(principalDetailsService.refreshAccessToken(dto));
    }

}
