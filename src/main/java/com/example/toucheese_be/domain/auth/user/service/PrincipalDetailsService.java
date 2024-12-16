package com.example.toucheese_be.domain.auth.user.service;

import com.example.toucheese_be.domain.auth.user.constant.Role;
import com.example.toucheese_be.domain.auth.user.constant.SocialType;
import com.example.toucheese_be.domain.auth.user.dto.request.CreateUserDto;
import com.example.toucheese_be.domain.auth.user.dto.request.SignInDto;
import com.example.toucheese_be.domain.auth.user.dto.response.UserDto;
import com.example.toucheese_be.domain.auth.user.entity.PrincipalDetails;
import com.example.toucheese_be.domain.auth.user.entity.User;
import com.example.toucheese_be.domain.auth.user.jwt.JwtTokenDto;
import com.example.toucheese_be.domain.auth.user.jwt.JwtTokenUtils;
import com.example.toucheese_be.domain.auth.user.repository.UserRepository;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    // 일반 회원가입
    public UserDto signUp(CreateUserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new GlobalCustomException(ErrorCode.SIGN_UP_DUPLICATED_EMAIL);
        }
        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new GlobalCustomException(ErrorCode.SIGN_UP_PASSWORD_CHECK_NOT_MATCH);
        }

        // 성명, 프로필 이미지, 휴대폰 번호는 null 인채로 생성
        User savedUser = userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .role(Role.MEMBER)
                .build());

        return UserDto.fromEntity(savedUser);
    }

    // 일반 로그인
    public JwtTokenDto signIn(SignInDto dto) {
        // 로그인 시만 DB 접근 (이외의 요청은 DB 접근 X)
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.SIGN_IN_EMAIL_NOT_FOUND));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new GlobalCustomException(ErrorCode.SIGN_IN_PASSWORD_NOT_MATCH);
        }

        // JWT 발급
        // 아래 코드가 유효하려면 회원가입 시점에 PrincipalDetails 가 만들어지는 코드를 추가해야함
        PrincipalDetails principalDetails = loadUserByUsername(user.getEmail());
        String accessToken = jwtTokenUtils.generateAccessToken(principalDetails);

        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

    // TODO: 로그아웃

    @Override
    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.USER_NOT_FOUND));
        return PrincipalDetails.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .authorities(user.getRole().getRoles())
                .build();
    }
}
