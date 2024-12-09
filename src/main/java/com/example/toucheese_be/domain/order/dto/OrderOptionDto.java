package com.example.toucheese_be.domain.order.dto;

import com.example.toucheese_be.domain.order.entity.OrderOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderOptionDto {
    // 옵션 ID
    private Long optionId;
    // 옵션 이름
    private String optionName;
    // 옵션 가격
    private Integer optionPrice;
    // 옵션 수량
    private Integer optionQuantity;

    public static OrderOptionDto fromEntity(OrderOption orderOption){
        return OrderOptionDto.builder()
                .optionId(orderOption.getId())
                .optionPrice(orderOption.getPrice())
                .optionQuantity(orderOption.getQuantity())
                .build();
    }
}
