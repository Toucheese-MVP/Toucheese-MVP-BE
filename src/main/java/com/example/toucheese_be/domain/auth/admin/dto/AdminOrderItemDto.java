package com.example.toucheese_be.domain.auth.admin.dto;

import java.util.ArrayList;
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
public class AdminOrderItemDto {
    private String itemName;
    private Integer itemPrice;
    private List<AdminOrderOptionDto> adminOrderOption = new ArrayList<>();
}
