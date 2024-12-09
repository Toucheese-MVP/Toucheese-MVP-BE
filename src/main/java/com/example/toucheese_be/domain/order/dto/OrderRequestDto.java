package com.example.toucheese_be.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDto {
    private String name;
    private String studio;
    private String email;
    private List<OrderItemDto> itemDtos;
}