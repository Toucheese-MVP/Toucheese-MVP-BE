package com.example.toucheese_be.global.util;

import com.example.toucheese_be.domain.auth.user.entity.PrincipalDetails;
import com.example.toucheese_be.domain.auth.user.entity.User;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof PrincipalDetails principalDetails) {
            return principalDetails.getUser();
        } else {
            throw new GlobalCustomException(ErrorCode.AUTHENTICATION_FAILED);
        }
    }
}
