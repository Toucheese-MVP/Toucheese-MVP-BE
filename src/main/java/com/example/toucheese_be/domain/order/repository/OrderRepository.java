package com.example.toucheese_be.domain.order.repository;

import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Order findOrdersWithDetails(Long orderId);

    List<Order> findByUserId(Long userId);

    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems oi JOIN FETCH oi.item i JOIN FETCH i.studio WHERE o.user.id = :userId")
    List<Order> findByUserIdWithDetails(@Param("userId") Long userId);
}
