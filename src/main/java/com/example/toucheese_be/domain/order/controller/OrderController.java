package com.example.toucheese_be.domain.order.controller;

import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestDto;
import com.example.toucheese_be.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    ) {
        boolean isOrderCreated = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(isOrderCreated);
    }


    // 예약 일정 탭 불러오기
    @GetMapping("/schedule")
    public ResponseEntity<List<OrderDetailDto>> getOrderSchedule() {

        List<OrderDetailDto> isOrderScheduled = orderService.checkedSchedule();

        return ResponseEntity.ok(isOrderScheduled);
    }

    //예약 취소
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Boolean> getCancelledSchedule(
            @PathVariable
            Long orderId
    ) {
        Boolean isCancelledSchedule = orderService.getCancelTheSchedule(orderId);;
        return ResponseEntity.ok(isCancelledSchedule);
    }

    // 예약 일정 상세보기
    @GetMapping("/schedule/detailed")
    public ResponseEntity<List<OrderDetailDto>> getViewDetailedSchedule(){
        List<OrderDetailDto> isViewDetailedSchedule = orderService.viewDetailedSchedule();

        return ResponseEntity.ok(isViewDetailedSchedule);
    }
}