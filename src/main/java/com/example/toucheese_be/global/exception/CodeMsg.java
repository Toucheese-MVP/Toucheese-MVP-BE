package com.example.toucheese_be.global.exception;

public class CodeMsg {

    // E403_1_NO("403-1", "권한이 없습니다.");

    private  final String code;
    private final String message;

    // enum 생성자
    CodeMsg(String code, String message){
        this.code = code;
        this.message = message;
    }

    // 코드를 반환하는 메소드
    public String getCode() {
        return code;
    }

    // 메시지를 반환하는 메소드
    public String getMessage() {
        return message;
    }
}