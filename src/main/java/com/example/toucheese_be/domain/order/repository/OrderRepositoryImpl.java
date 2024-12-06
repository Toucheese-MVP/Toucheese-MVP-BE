package com.example.toucheese_be.domain.order.repository;

import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;
import com.example.toucheese_be.domain.order.entity.QOrder;
import com.example.toucheese_be.domain.order.entity.QOrderItem;
import com.example.toucheese_be.domain.order.entity.QOrderOption;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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
        return jpaQueryFactory.selectFrom(order)
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .leftJoin(orderItem.orderOptions, orderOption).fetchJoin()
                .fetch().stream().map(AdminOrderDto::fromEntity)
                .collect(Collectors.toList());
    }
}
