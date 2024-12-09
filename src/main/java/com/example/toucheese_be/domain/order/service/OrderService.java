package com.example.toucheese_be.domain.order.service;

import com.example.toucheese_be.domain.auth.user.entity.User;
import com.example.toucheese_be.domain.auth.user.service.UserService;
import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.dto.OrderItemDto;
import com.example.toucheese_be.domain.order.dto.OrderOptionDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import com.example.toucheese_be.domain.order.entity.OrderOption;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import com.example.toucheese_be.domain.studio.dto.ConceptDto;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    // TODO: 주문 생성 컨트롤러 메서드에 대한 서비스 로직
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final StudioService studioService;
    //
    public boolean createOrder(OrderDetailDto orderDetailDto) {

    //public boolean processPayment(Order orderId) {
        //orderRepository.

        //return true;
    //}


    // Order 엔티티를 OrderDetailDto로 변환하는 메서드
    /*
    private OrderDetailDto convertToDto(Order order) {
        return OrderDetailDto.fromEntity(order);
    }*/

    // 장바구니 생성
    /*
    public Page<OrderDetailDto> getOrders(PageRequestDto dto, Pageable pageable){

        Page<Order> orders = orderRepository.findAll(pageable);

        return
    }


    // 주문서 조회
    public OrderDetailDto getSearchOrders(Long orderId) {
        // 주문 ID로 주문을 조회
        Order orders = orderRepository.findOrdersWithDetails(orderId);
        // 엔티티를 DTO로 변환
        return OrderDetailDto.fromEntity(orders);
    }
    */
}
