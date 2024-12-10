package com.example.toucheese_be.domain.order.service;

import com.example.toucheese_be.domain.auth.user.entity.User;
import com.example.toucheese_be.domain.order.dto.OrderItemDto;
import com.example.toucheese_be.domain.order.dto.OrderOptionDto;
import com.example.toucheese_be.domain.order.dto.OrderRequestDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import com.example.toucheese_be.domain.order.entity.OrderOption;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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

    //
    public boolean createOrder(OrderRequestDto orderRequest) {
        //사용자 정보 생성
        User user = new User();
        user.setEmail(orderRequest.getEmail());

        // 주문 객체 생성
        // 사용자 정보를 order에 세팅
        Order order = new Order();
        order.setId(orderRequest.getId());
        order.setStudio(orderRequest.getStudio());

        // 주문 항목 처리
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto itemDto : orderRequest.getItemDtos()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(itemDto.getItem());
            orderItem.setQuantity(itemDto.getQuantity());

            // 옵션 처리
            List<OrderOption> orderOptions =new ArrayList<>();
            for(OrderOptionDto optionDto : itemDto.getOrderOptionDtos()){
                OrderOption orderOption = new OrderOption();
                orderOption.setId(optionDto.getId()); // 원래 상품 이름이어야 하는 자리
                orderOption.setQuantity(optionDto.getQuantity());
            }

            orderItem.setOrderOptions(orderOptions);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        orderRepository.save(order);

        return true;
    }



    //public boolean processPayment(Order orderId) {
        //orderRepository.

        //return true;
    // }


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
