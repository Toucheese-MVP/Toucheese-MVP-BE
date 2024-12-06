package com.example.toucheese_be.domain.auth.admin.dto;

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
public class AdminOrderOptionDto {
    private String orderName;
    private Integer orderPrice;
    private Integer orderQuantity;
}
