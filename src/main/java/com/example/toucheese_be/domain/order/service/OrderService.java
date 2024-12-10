package com.example.toucheese_be.domain.order.service;

import com.example.toucheese_be.domain.auth.user.entity.User;
import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.ItemOption;
import com.example.toucheese_be.domain.item.repository.ItemOptionRepository;
import com.example.toucheese_be.domain.item.repository.ItemRepository;
import com.example.toucheese_be.domain.order.dto.OrderItemDto;
import com.example.toucheese_be.domain.order.dto.OrderOptionDto;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestDto;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestItemDto;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestOptionDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import com.example.toucheese_be.domain.order.entity.OrderOption;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import com.example.toucheese_be.domain.auth.user.repository.UserRepository;

import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.repository.StudioRepository;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final StudioRepository studioRepository;
    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public boolean createOrder(OrderRequestDto orderRequest) {
        // 사용자 정보 생성 및 저장
        User user = new User();
        user.setEmail(orderRequest.getEmail());
        user.setName(orderRequest.getName());
        user.setPhoneNumber(orderRequest.getPhone());
        userRepository.save(user);

        // 스튜디오 조회
        Studio studio = studioRepository.findById(orderRequest.getStudioId())
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.STUDIO_NOT_FOUND));

        // 주문 생성
        Order order = new Order();
        order.setStudio(studio);
        order.setUser(user);
        order.setOrderDateTime(orderRequest.getOrderDateTime());

        // 주문 항목 생성
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderRequestItemDto itemDto : orderRequest.getItemDtos()) {
            // Item 조회
            Item item = itemRepository.findById(itemDto.getItemId())
                    .orElseThrow(() -> new GlobalCustomException(ErrorCode.ITEM_NOT_FOUND));

            // OrderItem 생성
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setName(item.getName());
            orderItem.setQuantity(itemDto.getItemQuantity());

            // 옵션 총 가격 계산
            int optionsTotalPrice = 0;
            List<OrderOption> orderOptions = new ArrayList<>();
            for (OrderRequestOptionDto optionDto : itemDto.getOptionDtoList()) {
                // ItemOption 조회
                ItemOption itemOption = itemOptionRepository.findById(optionDto.getOptionId())
                        .orElseThrow(() -> new GlobalCustomException(ErrorCode.ITEM_OPTION_NOT_FOUND));

                // OrderOption 생성
                OrderOption orderOption = new OrderOption();
                orderOption.setOrderItem(orderItem);
                orderOption.setItemOptionId(itemOption);
                orderOption.setName(itemOption.getOption().getName());
                orderOption.setPrice(itemOption.getOption().getPrice());
                orderOption.setQuantity(optionDto.getOptionQuantity());

                // 옵션 총 가격 계산
                optionsTotalPrice += itemOption.getOption().getPrice() * optionDto.getOptionQuantity();
                orderOptions.add(orderOption);
            }

            // OrderItem에 옵션 설정
            orderItem.setOrderOptions(orderOptions);

            // 상품 총 가격 (상품 + 옵션)
            int itemTotalPrice = item.getPrice() * itemDto.getItemQuantity();
            orderItem.setTotalPrice(itemTotalPrice + optionsTotalPrice);

            orderItems.add(orderItem);
        }
        // Order에 OrderItem 설정
        order.setOrderItems(orderItems);

        // Order 저장
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
