package com.example.toucheese_be.domain.order.repository;

import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;

import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderItemDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import com.example.toucheese_be.domain.order.entity.QOrder;
import com.example.toucheese_be.domain.order.entity.QOrderItem;
import com.example.toucheese_be.domain.order.entity.QOrderOption;
import com.example.toucheese_be.domain.studio.entity.constant.StudioImageType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QOrder order = QOrder.order;
    private final QOrderItem orderItem = QOrderItem.orderItem;
    private final QOrderOption orderOption = QOrderOption.orderOption;

    @Override
    public List<AdminOrderDto> findAllOrdersWithDetails() {
        // 1. 주문과 주문 아이템 가져오기
        List<Order> ordersWithItems = jpaQueryFactory.selectFrom(order)
                .distinct()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .fetch();

        // 2. 주문 아이템과 옵션 정보를 가져오기
        List<OrderItem> itemsWithOptions = jpaQueryFactory.selectFrom(orderItem)
                .leftJoin(orderItem.orderOptions, orderOption).fetchJoin()
                .fetch();

        // 주문 아이템과 옵션 정보를 매핑할 Map 생성 (Order ID -> List<OrderItem>)
        Map<Long, List<OrderItem>> orderItemsMap = itemsWithOptions.stream()
                .collect(Collectors.groupingBy(orderItemEntity -> orderItemEntity.getOrder().getId()));

        // 최종 AdminOrderDto로 변환
        return ordersWithItems.stream().map(orderEntity -> AdminOrderDto.builder()
                        .orderId(orderEntity.getId())
                        .studioName(orderEntity.getStudio().getName())
                        .studioProfile(orderEntity.getStudio().getImages().stream()
                                .filter(studioImage -> studioImage.getType().equals(StudioImageType.PROFILE))
                                .toList().get(0).getImageUrl())
                        .orderDateTime(orderEntity.getOrderDateTime().toString())
                        .userName(orderEntity.getUser().getName())
                        .orderItems(orderItemsMap.getOrDefault(orderEntity.getId(), Collections.emptyList()).stream()
                                .map(AdminOrderItemDto::fromEntity)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
