package com.example.toucheese_be.domain.item.repository;

import com.example.toucheese_be.domain.item.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
