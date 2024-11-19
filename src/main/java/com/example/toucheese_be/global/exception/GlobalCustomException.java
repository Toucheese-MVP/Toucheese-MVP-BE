package com.example.toucheese_be.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalCustomException extends RuntimeException {
    private ErrorCode errorCode;

    public GlobalCustomException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
}
