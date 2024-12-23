package com.example.toucheese_be.global.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    USER_NEED_SIGH_UP(2000, HttpStatus.OK, "최초 로그인 입니다. 회원가입이 필요합니다."),
    USER_DONT_NEED_SIGN_UP(2001, HttpStatus.CREATED, "이전에 로그인을 한 적이 있습니다.");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
