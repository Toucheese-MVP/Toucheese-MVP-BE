package com.example.toucheese_be.domain.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReservedDetailDto {
    private Long id;
    private ReservedUserDto reservedUserDto;
    private List<ReservedOptionDto> reservedOptionDtos;
    private String reservedDateTime;
    private ReservedItemDto reservedItemDto;

    private int totalItemPrice;
}
