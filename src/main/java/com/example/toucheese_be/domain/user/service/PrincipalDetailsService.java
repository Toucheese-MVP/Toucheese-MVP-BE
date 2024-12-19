package com.example.toucheese_be.domain.user.service;

import com.example.toucheese_be.domain.user.constant.Role;
import com.example.toucheese_be.domain.user.constant.SocialProvider;
import com.example.toucheese_be.domain.user.dto.request.OAuthSignInDto;
import com.example.toucheese_be.domain.user.entity.PrincipalDetails;
import com.example.toucheese_be.domain.user.jwt.JwtTokenUtils;
import com.example.toucheese_be.domain.user.dto.request.CreateUserDto;
import com.example.toucheese_be.domain.user.dto.request.SignInDto;
import com.example.toucheese_be.domain.user.dto.response.UserDto;
import com.example.toucheese_be.domain.user.entity.User;
import com.example.toucheese_be.domain.user.jwt.JwtTokenDto;
import com.example.toucheese_be.domain.user.repository.UserRepository;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import java.util.Optional;
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
        return jwtTokenUtils.generateAccessToken(principalDetails);
    }

    // 소셜 로그인
    public JwtTokenDto oAuthSignIn(OAuthSignInDto dto) {
        // socialId 존재 유무 판단
        Optional<User> optionalUser = userRepository.findBySocialId(dto.getSocialId());
        PrincipalDetails principalDetails;

        // 로그인을 한적이 있는 경우
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info("{}님은 로그인을 한 적이 있습니다", user.getUsername());
            user.setEmail(dto.getEmail());
            user.setUsername(dto.getUsername());
            userRepository.save(user);
            principalDetails = new PrincipalDetails(user);
        }
        // 최초 로그인
        else {
            log.info("최초 로그인 입니다.");
            User user = userRepository.save(User.builder()
                    .socialId(dto.getSocialId())
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .socialProvider(SocialProvider.valueOf(dto.getSocialProvider()))
                    .role(Role.MEMBER)
                    .build());
            principalDetails = new PrincipalDetails(user);
        }

        // jwt 토큰 발급
        return jwtTokenUtils.generateAccessToken(principalDetails);
    }

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
