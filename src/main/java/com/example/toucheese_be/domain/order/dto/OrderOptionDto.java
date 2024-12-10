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
    private Long id;
    // 옵션 이름
    private String name;
    // 옵션 가격
    private Integer price;
    // 옵션 수량
    private Integer quantity;

    public static OrderOptionDto fromEntity(OrderOption orderOption){
        return OrderOptionDto.builder()
                .id(orderOption.getId())
                .price(orderOption.getPrice())
                .quantity(orderOption.getQuantity())
                .build();
    }
}
