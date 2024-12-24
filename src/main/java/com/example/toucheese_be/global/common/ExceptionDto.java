package com.example.toucheese_be.global.common;

import com.example.toucheese_be.global.common.constant.ErrorCode;
import jakarta.validation.constraints.NotNull;


public record ExceptionDto (
        @NotNull Integer code,
        @NotNull String message
) {

    public static ExceptionDto ofError(ErrorCode errorCode) {
        return new ExceptionDto(
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }
}
