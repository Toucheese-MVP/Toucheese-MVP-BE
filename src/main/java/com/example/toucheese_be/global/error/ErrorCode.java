package com.example.toucheese_be.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 스튜디오 관련 에러
    INVALID_FILTER_PARAMETER(400, "1001", "필터링 파라미터가 유효하지 않습니다."),
    REVIEW_NOT_FOUND(404, "1002", "해당 리뷰를 찾을 수 없습니다."),
    STUDIO_NOT_FOUND(404, "1003", "해당 스튜디오를 찾을 수 없습니다."),
    ITEM_NOT_FOUND(404, "1004", "해당 상품을 찾을 수 없습니다."),
    ITEM_OPTION_NOT_FOUND(404, "1005", "해당 상품 옵션을 찾을 수 없습니다."),
    ORDER_NOT_FOUND(404, "1006", "해당 예약 정보를 찾을 수 없습니다."),
    ORDER_ALREADY_APPROVED(409, "ORDER_ALREADY_APPROVED", "해당 예약 정보는 이미 승인 되었습니다."),
    ORDER_ALREADY_REJECTED(409, "ORDER_ALREADY_REJECTED", "해당 예약 정보는 이미 거절 되었습니다."),
    ORDER_ALREADY_CANCELLED(409, "ORDER_ALREADY_CANCELLED", "해당 예약 정보는 이미 취소 되었습니다."),

    // 회원관련 예외
    SIGN_UP_PASSWORD_CHECK_NOT_MATCH(400, "2001", "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    SIGN_UP_DUPLICATED_EMAIL(400, "2002", "이미 존재하는 이메일 입니다."),
    SIGN_IN_EMAIL_NOT_FOUND(404, "2003", "존재하지 않는 이메일입니다."),
    SIGN_IN_PASSWORD_NOT_MATCH(400, "2004", "비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND(404, "2005", "존재하지 않는 회원입니다."),
    UPDATE_USERNAME_EMPTY(400, "2006", "사용자 이름이 비어있습니다."),
    AUTHENTICATION_FAILED(403, "2006", "인증 확인에 실패했습니다."),




    // 토큰 관련 예외
    REFRESH_TOKEN_NOT_EQAUL(401, "3001", "Refresh Token 이 일치하지 않습니다."),
    ACCESS_TOKEN_EXPIRED(401, "3002", "Access Token 이 만료되었습니다.");

    private final int status;
    private final String code;
    private final String message;
}
