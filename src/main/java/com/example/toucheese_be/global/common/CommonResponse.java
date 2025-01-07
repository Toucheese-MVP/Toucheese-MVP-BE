package com.example.toucheese_be.global.common;

import com.example.toucheese_be.global.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;

@Schema(description = "공통 응답 DTO")
public record CommonResponse<T>(
        @Schema(description = "HTTP 상태 코드")
        @JsonIgnore
        HttpStatus httpStatus,
        @Schema(description = "성공 여부")
        boolean success, // 성공 유무
        @Schema(description = "데이터")
        @Nullable
        T payload, // 데이터를 담는 곳
        @Schema(description = "에러 내용")
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
