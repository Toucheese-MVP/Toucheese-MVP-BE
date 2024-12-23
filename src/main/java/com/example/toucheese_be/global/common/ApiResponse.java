package com.example.toucheese_be.global.common;

import com.example.toucheese_be.global.common.constant.ErrorCode;
import com.example.toucheese_be.global.common.constant.SuccessCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;


public record ApiResponse<T>(
        @JsonIgnore
        HttpStatus httpStatus,
        boolean success, // 성공 유무
        @Nullable
        Object successDetails, // 성공 내용
        @Nullable
        T payload, // 데이터를 담는 곳
        @Nullable
        Object error // 에러 내용

) {
    // 일반적인 성공
    public static <T> ApiResponse<T> ok(@Nullable final T data) {
        return new ApiResponse<>(HttpStatus.OK, true, null, data, null);
    }

    // 커스텀 성공
    public static <T> ApiResponse<T> success(@Nullable final T data, final SuccessCode successCode) {
        return new ApiResponse<>(successCode.getHttpStatus(), true, SuccessDto.ofSuccess(successCode), data, null);
    }

    // 커스텀 실패
    public static <T> ApiResponse<T> fail(final ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getHttpStatus(), false, null, null, ExceptionDto.ofError(errorCode));
    }
}
