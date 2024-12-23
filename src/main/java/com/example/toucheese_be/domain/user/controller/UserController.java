package com.example.toucheese_be.domain.user.controller;


import com.example.toucheese_be.domain.user.dto.request.CreateUserDto;
import com.example.toucheese_be.domain.user.dto.request.OAuthSignInDto;
import com.example.toucheese_be.domain.user.dto.request.SignInDto;
import com.example.toucheese_be.domain.user.dto.request.UpdateUserDto;
import com.example.toucheese_be.global.common.ApiResponse;
import com.example.toucheese_be.domain.user.jwt.TokenResponseDto;
import com.example.toucheese_be.domain.user.service.PrincipalDetailsService;
import com.example.toucheese_be.domain.user.dto.response.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final PrincipalDetailsService principalDetailsService;

    // 일반 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp (
            @Valid @RequestBody
            CreateUserDto dto
    ) {
        return ResponseEntity.ok(principalDetailsService.signUp(dto));
    }

    // 일반 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(
        @RequestBody
        SignInDto dto
    ) {
        return ResponseEntity.ok(principalDetailsService.signIn(dto));
    }

    // 소셜 로그인
    @PostMapping("/sign-in/oauth")
    public ApiResponse<TokenResponseDto> oAuthSignIn(
            @RequestBody
            OAuthSignInDto dto
    ) {
        return principalDetailsService.oAuthSignIn(dto);
    }

    // 추가 정보 업데이트
    @PutMapping("profile/update")
    public Boolean profileUpdate(
            @RequestBody
            UpdateUserDto dto
    ) {
        return principalDetailsService.profileUpdate(dto);
    }
}
