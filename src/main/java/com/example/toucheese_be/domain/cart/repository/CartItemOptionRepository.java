package com.example.toucheese_be.domain.cart.repository;

import com.example.toucheese_be.domain.cart.entity.CartItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemOptionRepository extends JpaRepository<CartItemOption, Long> {
}
