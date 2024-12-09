package com.example.toucheese_be.domain.order.controller;

import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.dto.OrderRequestDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.service.OrderService;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import com.example.toucheese_be.domain.studio.dto.StudioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    // TODO: 주문을 만드는 주문 생성 컨트롤러

    private final OrderService orderService;

    // 주문서 결제(성공 = true, 실패 = false)

    @PostMapping("/create")
    public ResponseEntity<Boolean> getCreateOrder(
            @RequestBody
            OrderRequestDto orderRequestDto){

                boolean isOrderCreated = orderService.createOrder(orderRequestDto);

                return ResponseEntity.ok(isOrderCreated);
        }

    // boolean paymentSuccess = orderService.processPayment(orderId);

    /* 장바구니 생성 시 필요할 로직

    public ResponseEntity<OrderDetailDto> getOrderDetails(
            Order orderId
    ) {
        OrderDetailDto orderDetails = orderService.getSearchOrders(orderId.getId());

        return ResponseEntity.ok(orderDetails);
    }*/
}