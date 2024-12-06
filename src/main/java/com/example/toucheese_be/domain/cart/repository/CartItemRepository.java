package com.example.toucheese_be.domain.cart.repository;

import com.example.toucheese_be.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
