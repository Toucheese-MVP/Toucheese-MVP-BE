package com.example.toucheese_be.domain.item.repository;

import com.example.toucheese_be.domain.item.entity.CartItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemOptionRepository extends JpaRepository<CartItemOption, Long> {
}
