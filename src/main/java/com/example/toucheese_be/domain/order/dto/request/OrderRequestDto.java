package com.example.toucheese_be.domain.order.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDto {
    private String name;
    private String email;
    private String phone;
    private Long studioId;
    private LocalDateTime orderDateTime;
    private List<OrderRequestItemDto> itemDtos;
}