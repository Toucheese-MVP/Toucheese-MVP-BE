package com.example.toucheese_be.domain.auth.admin.dto;

import com.example.toucheese_be.domain.item.entity.Option;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderOption;
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
    private Long optionId;
    private String optionName;
    private Integer optionPrice;
    private Integer optionQuantity;
}
