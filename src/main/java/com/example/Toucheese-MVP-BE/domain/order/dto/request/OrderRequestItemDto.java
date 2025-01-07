package com.example.toucheese_be.domain.order.dto.request;

import java.util.List;
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
public class OrderRequestItemDto {
    private Long itemId;
    private Integer itemQuantity;
    private List<OrderRequestOptionDto> orderRequestOptionDtos;
}
