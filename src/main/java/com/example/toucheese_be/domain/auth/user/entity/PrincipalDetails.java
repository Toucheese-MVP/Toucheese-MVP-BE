package com.example.toucheese_be.domain.auth.user.entity;

import com.example.toucheese_be.domain.auth.user.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@Builder
public class PrincipalDetails implements UserDetails, OAuth2User {
    private User user;
    private Map<String, Object> attributes;

    // 일반 로그인
    public PrincipalDetails(User user) {
        this.user = user;
        this.attributes = null;
    }

    // TODO: OAuth2 로그인 PrincipalDetails 생성자
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String[] rawAuthorities = user.getRole().getRoles().split(",");
        for (String rawAuthority : rawAuthorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(rawAuthority));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    // OAuth2 메서드
    @Override
    public String getName() {
        return user.getEmail();
    }
}
