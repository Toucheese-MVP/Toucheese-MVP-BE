package com.example.toucheese_be.domain.item.repository;

import com.example.toucheese_be.domain.item.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
