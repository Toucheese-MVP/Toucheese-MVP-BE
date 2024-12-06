package com.example.toucheese_be.domain.cart.service;

import com.example.toucheese_be.domain.cart.repository.CartItemOptionRepository;
import com.example.toucheese_be.domain.cart.repository.CartItemRepository;
import com.example.toucheese_be.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemOptionRepository cartItemOptionRepository;

    // 장바구니에 상품 추가 로직

    // 장바구니 조회 로직

    // 장바구니에 상품 삭제 로직

    // 장바구니 옵션 변경 로직
}
