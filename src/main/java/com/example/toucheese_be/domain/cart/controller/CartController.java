package com.example.toucheese_be.domain.cart.controller;


import com.example.toucheese_be.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 장바구니에 상품 추가
    @PostMapping
    public void addCartItem() {};

    // 장바구니 조회
    @GetMapping
    public void readAllCartItems() {}


    // 장바구니에 상품 삭제
    @DeleteMapping
    public void deleteCartItem() {};


    // 장바구니 옵션 변경
    @PutMapping
    public void updateCartItem() {};
}
