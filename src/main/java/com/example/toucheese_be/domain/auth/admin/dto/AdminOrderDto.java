package com.example.toucheese_be.domain.auth.admin.dto;

import com.example.toucheese_be.domain.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrderDto {
    private Long orderId;
    private String studioProfile;
    private String studioName;
    private String orderDateTime;
    private String userName;
    private List<AdminOrderItemDto> orderItemDtos = new ArrayList<>();

    public static AdminOrderDto fromEntity(Order orderEntity) {
        List<AdminOrderItemDto> orderItemDtos = orderEntity.getOrderItems().stream()
                .map(orderItemEntity -> AdminOrderItemDto.builder()
                        .itemName(orderItemEntity.getItem().getName())
                        .itemPrice(orderItemEntity.getItem().getPrice())
                        .adminOrderOption(orderItemEntity.getOrderOptions().stream().map(orderOptionEntity ->
                                        AdminOrderOptionDto.builder()
                                                .optionName(orderOptionEntity.getItemOption().getOption().getName())
                                                .optionPrice(orderOptionEntity.getPrice())
                                                .optionQuantity(orderOptionEntity.getQuantity())
                                                .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return AdminOrderDto.builder()
                .orderId(orderEntity.getId())
                .orderDateTime(orderEntity.getOrderDateTime().toString())
                .userName(orderEntity.getUser().getName())
                .orderItemDtos(orderItemDtos)
                .build();
    }
}
