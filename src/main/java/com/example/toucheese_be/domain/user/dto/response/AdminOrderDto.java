package com.example.toucheese_be.domain.user.dto.response;

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
public class AdminOrderDto {
    private Long orderId;
    private String studioProfile;
    private String studioName;
    private String orderDateTime;
    private String userName;
    private List<AdminOrderItemDto> orderItems;
}
