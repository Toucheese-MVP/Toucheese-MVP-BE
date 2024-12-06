package com.example.toucheese_be.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderOptionDto {
    // 옵션 ID
    private Long optionId;
    // 옵션 이름
    private String optionName;
    // 옵션 가격
    private Integer optionPrice;
    // 옵션 수량
    private Integer optionQuantity;
}
