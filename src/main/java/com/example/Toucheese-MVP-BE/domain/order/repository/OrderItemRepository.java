package com.example.toucheese_be.domain.order.repository;

import com.example.toucheese_be.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
