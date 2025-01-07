//package com.example.toucheese_be.domain.item.controller;
//
//
//import com.example.toucheese_be.domain.item.service.CartService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/cart")
//@RequiredArgsConstructor
//public class CartController {
//    private final CartService cartService;
//
//
//    //장바구니에 상품 추가
//    @PostMapping("/items/add")
//    public ResponseEntity<Boolean> addCartItem(
//        @RequestBody
//        OrderRequestDto dto
//    ) {
//        return cartService.addCartItem(dto);
//    };
//
//    // 장바구니 조회
//    @GetMapping
//    public void readAllCartItems() {}
//
//
//    // 장바구니에 상품 삭제
//    @DeleteMapping
//    public void deleteCartItem() {};
//
//
//    // 장바구니 옵션 변경
//    @PutMapping
//    public void updateCartItem() {};
//}
