package com.example.toucheese_be.domain.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderScheduleItemDto {
    // 상품 ID
    private Long itemId;
    // 상품 이름
    private String name;
}
