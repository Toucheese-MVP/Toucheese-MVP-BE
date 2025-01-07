package com.example.toucheese_be.domain.user.controller;

import com.example.toucheese_be.domain.user.dto.request.OAuthSignInDto;
import com.example.toucheese_be.domain.user.dto.request.UpdateUserDto;
import com.example.toucheese_be.domain.user.dto.response.SocialLoginDto;
import com.example.toucheese_be.domain.user.dto.response.UserDto;
import com.example.toucheese_be.domain.user.jwt.TokenRequestDto;
import com.example.toucheese_be.global.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "06. [유저] (토큰 필요 O)", description = "유저 관련 API")
public interface UserApi {
    @PostMapping("/sign-in/oauth")
    @Operation(
            summary = "소셜 로그인",
            description = "<p>소셜 Provider (구글, 카카오, 애플) 리소스 서버로 부터 받아온 사용자 정보를 가지고 요청</p>"
                    + "<p>로그인 성공 시 JWT(accessToken, refreshToken)를 발급됩니다. </p> "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공 응답(최초 로그인)",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "201", description = "성공 응답(로그인 한 적이 있는 경우)",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    CommonResponse<SocialLoginDto> oAuthSignIn(
            @RequestBody
            OAuthSignInDto dto
    );

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
    ResponseEntity<SocialLoginDto> refreshAccessToken(
            @RequestBody
            TokenRequestDto dto
    );

    // 추가 정보 업데이트
    @PutMapping("/profile/update")
    @Operation(
            summary = "추가 정보 업데이트",
            description = "<p>사용자의 추가 정보를 업데이트합니다.</p>"
    )
    Boolean profileUpdate(
            @RequestBody
            UpdateUserDto dto
    );

    // 인증된 사용자 정보 조회
    @Operation(
            summary = "인증된 사용자 정보 조회",
            description = "<p>인증된 사용자의 정보를 조회합니다.</p>"
    )
    @GetMapping("/details")
    CommonResponse<UserDto> getUserDetails();

}
