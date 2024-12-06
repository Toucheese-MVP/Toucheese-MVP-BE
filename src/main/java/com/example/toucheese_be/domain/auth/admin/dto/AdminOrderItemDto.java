package com.example.toucheese_be.domain.auth.admin.dto;

import com.example.toucheese_be.domain.order.entity.OrderItem;
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
public class AdminOrderItemDto {
    private Long itemId;
    private String itemName;
    private Integer itemPrice;
    private List<AdminOrderOptionDto> adminOrderOptions = new ArrayList<>();

    public static AdminOrderItemDto fromEntity(OrderItem entity) {
        return AdminOrderItemDto.builder()
                .itemId(entity.getItem().getId())
                .itemName(entity.getItem().getName())
                .itemPrice(entity.getItem().getPrice())
                .adminOrderOptions(entity.getOrderOptions().stream()
                        .map(orderOption -> AdminOrderOptionDto.builder()
                                .optionId(orderOption.getId())
                                .optionName(orderOption.getItemOption().getOption().getName())
                                .optionPrice(orderOption.getPrice())
                                .optionQuantity(orderOption.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
