package com.example.toucheese_be.domain.order.entity;

import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.dto.OrderItemDto;
import com.example.toucheese_be.domain.order.dto.OrderOptionDto;
import com.example.toucheese_be.domain.order.dto.OrderUserDto;
import com.example.toucheese_be.domain.order.entity.constant.OrderStatus;
import com.example.toucheese_be.domain.user.entity.User;
import com.example.toucheese_be.domain.studio.entity.Studio;
import io.micrometer.common.KeyValues;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
/* 주문 엔티티 */
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDateTime;
    private Integer totalPrice;

    // Studio과 연결
    @ManyToOne
    @JoinColumn(name = "studio_id", nullable = false)
    private Studio studio;

    // 사용자와 연결
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.KEEP_RESERVATION;

    // 주문 상품과 연결
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<OrderItem> orderItems;

    public List<OrderDetailDto> getOrderDetails() {
        List<OrderDetailDto> orderDetails = new ArrayList<>();

        // 주문 사용자 정보 DTO 생성
        OrderUserDto orderUserDto = OrderUserDto.builder()
                .userId(this.user != null ? this.user.getId() : null) // 사용자 ID
                .userName(this.user != null ? this.user.getUsername() : null) // 사용자 이름
                .userEmail(this.user != null ? this.user.getEmail() : null) // 사용자 이메일
                .userPhone(this.user != null ? this.user.getPhone() : null) // 사용자 연락처
                .build();

        // 예약 시간 포맷팅 (예: "yyyy-MM-dd HH:mm:ss" 형식으로 변환)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String reservedDateTime = this.orderDateTime.format(formatter);

        // 주문 상품 정보 DTO 생성
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItemDto orderItemDto = OrderItemDto.builder()
                    .itemId(orderItem.getItem().getId())
                    .itemName(orderItem.getItem().getName())
                    .item(orderItem.getItem()) // 필요에 따라 Item 객체를 포함
                    .itemImage(orderItem.getItem().getImage()) // 상품 이미지 URL
                    .quantity(orderItem.getQuantity())
                    .totalPrice(orderItem.getTotalPrice())
                    .orderOptionDtos(orderItem.getOrderOptions().stream()
                            .map(option -> OrderOptionDto.builder()
                                    .id(option.getId())
                                    .name(option.getName())
                                    .price(option.getPrice())
                                    .quantity(option.getQuantity())
                                    .build())
                            .collect(Collectors.toList())) // 옵션 DTO 리스트
                    .build();

            orderItemDtos.add(orderItemDto);
        }

        // 최종 DTO 생성
        OrderDetailDto detailDto = OrderDetailDto.builder()
                .orderId(this.id)
                .orderUserDto(orderUserDto) // 주문 사용자 정보 DTO
                .studioName(this.studio.getName()) // 스튜디오 이름
                .reservedDateTime(reservedDateTime) // 예약 시간
                .orderItemDto(orderItemDtos) // 주문 상품 DTO 리스트
                .build();

        orderDetails.add(detailDto);

        return orderDetails;
    }

}
//    public List<OrderDetailDto> getOrderDetails() {
//        return orderItems.stream()
//                .map(orderItem -> new OrderDetailDto(
//                        orderItem.getId(),
//                        orderItem.getItem().getName(),
//                        orderItem.getQuantity(),
//                        orderItem.getTotalPrice()
//                )).collect(Collectors.toList());
//    }
