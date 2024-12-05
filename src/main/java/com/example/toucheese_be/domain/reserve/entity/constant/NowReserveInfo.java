package com.example.toucheese_be.domain.reserve.entity.constant;

import lombok.Getter;

@Getter
public enum NowReserveInfo {
    KEEP("진행 중"),
    SUCCESS("예약 성공"),
    FAIL("예약 취소");

    private final String nowReserveInfo;

    NowReserveInfo(String nowReserveInfo) {
        this.nowReserveInfo = nowReserveInfo;
    }
}
