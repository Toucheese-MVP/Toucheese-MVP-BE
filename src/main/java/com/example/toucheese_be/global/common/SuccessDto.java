package com.example.toucheese_be.global.common;

import com.example.toucheese_be.global.common.constant.SuccessCode;
import jakarta.validation.constraints.NotNull;

public record SuccessDto(
        @NotNull Integer code,
        @NotNull String message
) {
    public static ExceptionDto ofSuccess(SuccessCode successCode) {
        return new ExceptionDto(
                successCode.getCode(),
                successCode.getMessage()
        );
    }
}
