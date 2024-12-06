package com.example.toucheese_be.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDetailDto {
    // 주문 ID
    private Long orderId;
    // 내 정보
    private OrderUserDto OrderUserDto;
    // 스튜디오 이름
    private String studioName;
    // 예약 시간
    private String reservedDateTime;
    // 상품 확인
    private OrderItemDto OrderItemDto;
}
