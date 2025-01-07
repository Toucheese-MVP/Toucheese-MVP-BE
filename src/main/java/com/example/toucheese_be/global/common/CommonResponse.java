package com.example.toucheese_be.global.common;

import com.example.toucheese_be.global.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;


public record CommonResponse<T>(
        @JsonIgnore
        HttpStatus httpStatus,
        boolean success, // 성공 유무
        @Nullable
        T payload, // 데이터를 담는 곳
        @Nullable
        Object error // 에러 내용

) {
    // 일반적인 성공
    public static <T> CommonResponse<T> ok(@Nullable final T data) {
        return new CommonResponse<>(HttpStatus.OK, true, data, null);
    }

    public static <T> CommonResponse<T> created(@Nullable final T data) {
        return new CommonResponse<>(HttpStatus.CREATED, true, data, null);
    }

    // 커스텀 실패
    public static <T> CommonResponse<T> fail(final ErrorCode errorCode) {
        return new CommonResponse<>(errorCode.getHttpStatus(), false, null, ExceptionDto.ofError(errorCode));
    }
}
