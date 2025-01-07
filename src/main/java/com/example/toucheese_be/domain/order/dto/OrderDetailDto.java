package com.example.toucheese_be.domain.order.dto;

import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    // 주문 ID
    private Long orderId;

    private Long studioId;
    // 내 정보
    private OrderUserDto orderUserDto;
    // 스튜디오 이름
    private String studioName;
    // 예약 시간
    private String reservedDateTime;
    // 상품 확인
    private OrderItemDto orderItemDto;
    // 예약 상태
    private OrderStatus status;
    // 수정 가능 여부
    private boolean modifiable;

    private String studioImage;
}
//    public static OrderDetailDto fromEntity(Order entity) {
//        return OrderDetailDto.builder()
//                .orderId(entity.getId())
//                .orderUserDto(OrderUserDto.fromEntity(entity.getUser())) // User 객체에서 OrderUserDto로 변환
//                .studioName(entity.getStudio().getName())
//                .reservedDateTime(entity.getOrderDateTime().toString()) // 필요에 따라 형식 변환
//                .orderItemDto(entity.getOrderItems().stream()
//                        .map(OrderItemDto::fromEntity) // OrderItemDto 변환 필요
//                        .collect(Collectors.toList()))
//                .build();
//
//    }

