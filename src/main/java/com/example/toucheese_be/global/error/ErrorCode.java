package com.example.toucheese_be.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 예외 코드 부분 (추후 작성)
    INVALID_FILTER_PARAMETER(400, "FILTER_INVALID_PARAMETER", "필터링 파라미터가 유효하지 않습니다."),
    REVIEW_NOT_FOUND(404, "REVIEW_NOT_FOUND", "해당 리뷰를 찾을 수 없습니다."),

    // 예약 관련 예외 처리
    ORDER_NOT_FOUND(404, "ORDER_NOT_FOUND", "해당 예약 정보를 찾을 수 없습니다."),
    ORDER_ALREADY_APPROVED(409, "ORDER_ALREADY_APPROVED", "해당 예약 정보는 이미 승인 되었습니다."),
    ORDER_ALREADY_REJECTED(409, "ORDER_ALREADY_REJECTED", "해당 예약 정보는 이미 거절 되었습니다.");

    private final int status;
    private final String code;
    private final String message;
}
