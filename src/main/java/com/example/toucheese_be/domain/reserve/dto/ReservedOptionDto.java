package com.example.toucheese_be.domain.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservedOptionDto {
    private String itemOption;
    private Integer optionPrice;
}
