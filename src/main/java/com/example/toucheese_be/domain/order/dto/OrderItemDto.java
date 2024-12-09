package com.example.toucheese_be.domain.order.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemDto {
    // 싱품 ID
    private Long id;
    // 상품 가격
    private String name;

    private Item item;
    // 상품 이미지
    private String itemImage;
    // 상품 가격
    private Integer quantity;
    // 최종 가격
    private Integer totalPrice;
    // 상품에 대한 옵션
    private List<OrderOptionDto> orderOptionDtos = new ArrayList<>();

    public static OrderItemDto fromEntity(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .name(orderItem.getItem().getName())
                .itemImage(orderItem.getItem().getImage())
                .quantity(orderItem.getQuantity())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }
}