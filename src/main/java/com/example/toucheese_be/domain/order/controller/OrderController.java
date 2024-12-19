package com.example.toucheese_be.domain.order.controller;

import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestDto;
import com.example.toucheese_be.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> getCreateOrder(
            @RequestBody
            OrderRequestDto orderRequestDto
    ){
        boolean isOrderCreated = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(isOrderCreated);
    }

    @GetMapping("/{userId}/schedule")
    public ResponseEntity<Map<String, List<OrderDetailDto>>> getOrderSchedule(
            @PathVariable
            Long userId
    ){
        Map<String, List<OrderDetailDto>> isOrderScheduled = orderService.checkedSchedule(userId);

        return ResponseEntity.ok(isOrderScheduled);
    }
}