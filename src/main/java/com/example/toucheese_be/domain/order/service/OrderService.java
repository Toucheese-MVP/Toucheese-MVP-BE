package com.example.toucheese_be.domain.order.service;

import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.entity.constant.OrderStatus;
import com.example.toucheese_be.domain.user.entity.User;
import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.ItemOption;
import com.example.toucheese_be.domain.item.repository.ItemOptionRepository;
import com.example.toucheese_be.domain.item.repository.ItemRepository;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import com.example.toucheese_be.domain.order.entity.OrderOption;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import com.example.toucheese_be.domain.user.repository.UserRepository;

import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.repository.StudioRepository;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import com.example.toucheese_be.global.exception.AlreadyCancelledException;
import com.example.toucheese_be.global.exception.OrderNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final StudioRepository studioRepository;
    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;
    private final OrderRepository orderRepository;

    // 추후 querydsl 로 쿼리 성능 최적화할 예정
    @Transactional
    public boolean createOrder(OrderRequestDto dto) {
        // 사용자 정보 생성 및 저장
        User user = userRepository.save(User.builder()
                .username(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build());

        // 스튜디오 조회
        Studio studio = studioRepository.findById(dto.getStudioId())
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.STUDIO_NOT_FOUND));

        // 주문 생성
        Order order = Order.builder()
                .studio(studio)
                .user(user)
                .orderDateTime(dto.getOrderDateTime())
                .build();

        // 주문 상품 생성
        List<OrderItem> orderItems = dto.getOrderRequestItemDtos().stream()
                .map(orderRequestItemDto -> {
                    // item 조회
                    Item item = itemRepository.findById(orderRequestItemDto.getItemId())
                            .orElseThrow(() -> new GlobalCustomException(ErrorCode.ITEM_NOT_FOUND));

                    // 옵션 처리
                    List<OrderOption> orderOptions = orderRequestItemDto.getOrderRequestOptionDtos().stream()
                            .map(orderRequestOptionDto -> {
                                ItemOption itemOption = itemOptionRepository.findById(orderRequestOptionDto.getOptionId())
                                        .orElseThrow(() -> new GlobalCustomException(ErrorCode.ITEM_OPTION_NOT_FOUND));

                                return OrderOption.builder()
                                        .itemOptionId(itemOption)
                                        .name(itemOption.getOption().getName())
                                        .price(itemOption.getOption().getPrice())
                                        .quantity(orderRequestOptionDto.getOptionQuantity())
                                        .build();
                            })
                            .toList();

                    int orderOptionsTotalPrice = orderOptions.stream()
                            .mapToInt(option -> option.getPrice() * option.getQuantity())
                            .sum();

                    int totalOrderItemPrice =
                            item.getPrice() * orderRequestItemDto.getItemQuantity() + orderOptionsTotalPrice;

                    // OrderItem 생성
                    return OrderItem.builder()
                            .item(item)
                            .name(item.getName())
                            .price(item.getPrice())
                            .quantity(orderRequestItemDto.getItemQuantity())
                            .totalPrice(totalOrderItemPrice)
                            .order(order)
                            .build();
                })
                .toList();

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return true;
    }

    // 예약 일정 탭 구현
    public Map<String, List<OrderDetailDto>> checkedSchedule(Long userId) {
        // 사용자 예약 주문을 사용자 ID로 조회
        List<Order> orders = orderRepository.findByUserId(userId);

        // 결과를 저장할 맵 생성
        Map<String, List<OrderDetailDto>> scheduleMap = new HashMap<>();
        scheduleMap.put("다가오는 예약 일정", new ArrayList<>());
        scheduleMap.put("이전 예약 일정", new ArrayList<>());

        // 주문이 없는 경우 빈 리스트를 반환

        if(orders.isEmpty()){
            return scheduleMap;
        }
        // 주문 상태에 따라 리스트에 추가
        for(Order order : orders){
            OrderStatus orderStatus = order.getStatus();

            // 주문 세부 정보를 DTO로 변환
            List<OrderDetailDto> orderDetailDtos = order.getOrderDetails();

            // 주문 상태에 따라 리스트에 추가
            switch (orderStatus){
                case KEEP_RESERVATION:
                    scheduleMap.get("다가오는 예약 일정").addAll(orderDetailDtos);
                    break;

                case CANCEL_RESERVATION:
                case FINISHED_FILM:
                case CONFIRM_RESERVATION:
                    scheduleMap.get("이전 예약 일정").addAll(orderDetailDtos);
                    break;


            }

//
//            if (orderStatus.equals(OrderStatus.KEEP_RESERVATION)) {
//                // "다가오는 예약 일정"에 추가
//                scheduleMap.get("다가오는 예약 일정").addAll(orderDetailDtos);
//            } else if (orderStatus.equals(OrderStatus.CONFIRM_RESERVATION)) {
//                // "이전 예약 일정"에 추가
//                scheduleMap.get("이전 예약 일정").addAll(orderDetailDtos);
//            } else if (orderStatus.equals(OrderStatus.CANCEL_RESERVATION)) {
//                // "이전 예약 일정"에 추가
//                scheduleMap.get("이전 예약 일정").addAll(orderDetailDtos);
//            }else {
//
//            }

        }

        return scheduleMap;
    }

    public String getCancelTheSChedule(Long orderId, OrderDetailDto orderDetailDto) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if(order.getStatus() == OrderStatus.CANCEL_RESERVATION){
            throw new AlreadyCancelledException(orderId);
        }

        order.setStatus(OrderStatus.CANCEL_RESERVATION);
        orderRepository.save(order);

        return "예약이 취소되었습니다.";
    }
}