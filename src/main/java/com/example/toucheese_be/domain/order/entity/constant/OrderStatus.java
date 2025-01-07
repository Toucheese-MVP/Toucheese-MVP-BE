package com.example.toucheese_be.domain.order.entity.constant;

import lombok.Getter;

@Getter
public enum OrderStatus {
    KEEP_RESERVATION("예약 대기"),
    CONFIRM_RESERVATION("예약 확정"),
    FINISHED_FILM("촬영 완료"),
    CANCEL_RESERVATION("예약 취소");

    private final String nowReserveInfo;

    OrderStatus(String nowReserveInfo) {
        this.nowReserveInfo = nowReserveInfo;
    }
}
