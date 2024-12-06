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
    private List<AdminOrderItemDto> orderItemDtos = new ArrayList<>();

    public static AdminOrderDto fromEntity(Order orderEntity) {
        List<AdminOrderItemDto> orderItemDtos = orderEntity.getOrderItems().stream()
                .map(orderItemEntity -> AdminOrderItemDto.builder()
                        .itemName(orderItemEntity.getItem().getName())
                        .itemPrice(orderItemEntity.getItem().getPrice())
                        .adminOrderOption(orderItemEntity.getOrderOptions().stream().map(orderOptionEntity ->
                                        AdminOrderOptionDto.builder()
                                                .optionName(orderOptionEntity.getItemOption().getOption().getName())
                                                .optionPrice(orderOptionEntity.getPrice())
                                                .optionQuantity(orderOptionEntity.getQuantity())
                                                .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        String studioProfileUrl = orderEntity.getStudio().getImages().stream()
                .filter(image -> image.getType() == StudioImageType.PROFILE)
                .findFirst()
                .map(StudioImage::getImageUrl)
                .orElse(null);


        return AdminOrderDto.builder()
                .orderId(orderEntity.getId())
                .studioProfile(studioProfileUrl)
                .studioName(orderEntity.getStudio().getName())
                .orderDateTime(orderEntity.getOrderDateTime().toString())
                .userName(orderEntity.getUser().getName())
                .orderItemDtos(orderItemDtos)
                .build();
    }
}
