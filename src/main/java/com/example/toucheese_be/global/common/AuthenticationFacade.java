package com.example.toucheese_be.global.common;

import com.example.toucheese_be.domain.user.entity.PrincipalDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public PrincipalDetails getAuth() {
        return (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}