package com.example.toucheese_be.domain.auth.admin.dto;

import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.studio.entity.StudioImage;
import com.example.toucheese_be.domain.studio.entity.constant.StudioImageType;
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
public class AdminOrderDto {
    private Long orderId;
    private String studioProfile;
    private String studioName;
    private String orderDateTime;
    private String userName;
    private List<AdminOrderItemDto> orderItems = new ArrayList<>();
}
