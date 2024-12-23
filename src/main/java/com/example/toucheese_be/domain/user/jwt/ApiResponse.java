package com.example.toucheese_be.domain.user.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String code;
    private String message;
    private T data;

    public ApiResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}