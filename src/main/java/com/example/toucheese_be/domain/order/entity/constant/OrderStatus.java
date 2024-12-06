package com.example.toucheese_be.domain.order.entity.constant;

import lombok.Getter;

@Getter
public enum OrderStatus {
    KEEP("진행 중"),
    SUCCESS("예약 성공"),
    FAIL("예약 취소");

    private final String nowReserveInfo;

    OrderStatus(String nowReserveInfo) {
        this.nowReserveInfo = nowReserveInfo;
    }
}
