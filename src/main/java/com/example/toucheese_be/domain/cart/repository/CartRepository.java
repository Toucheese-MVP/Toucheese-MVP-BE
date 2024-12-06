package com.example.toucheese_be.domain.cart.repository;

import com.example.toucheese_be.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
