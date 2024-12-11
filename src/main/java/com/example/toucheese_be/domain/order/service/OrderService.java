package com.example.toucheese_be.domain.order.service;

import com.example.toucheese_be.domain.auth.user.entity.User;
import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.ItemOption;
import com.example.toucheese_be.domain.item.repository.ItemOptionRepository;
import com.example.toucheese_be.domain.item.repository.ItemRepository;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestDto;
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

import java.util.List;

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

        // 주문 상품 생성
        List<OrderItem> orderItems = dto.getOrderRequestItemDtos().stream()
                .map(orderRequestItemDto -> {
                    // item 조회
                    Item item = itemRepository.findById(orderRequestItemDto.getItemId())
                            .orElseThrow(() -> new GlobalCustomException(ErrorCode.ITEM_NOT_FOUND));

                    // 옵션 처리
                    List<OrderOption> orderOptions = orderRequestItemDto.getOrderRequestOptionDtos().stream()
                            .map(orderRequestOptionDto -> {
                                ItemOption itemOption = itemOptionRepository.findById(
                                                orderRequestOptionDto.getOptionId())
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

                    int totalOrderItemPrice = item.getPrice() * orderRequestItemDto.getItemQuantity() + orderOptionsTotalPrice;

                    // OrderItem 생성
                    OrderItem orderItem = OrderItem.builder()
                            .item(item)
                            .name(item.getName())
                            .price(item.getPrice())
                            .quantity(orderRequestItemDto.getItemQuantity())
                            .totalPrice(totalOrderItemPrice)
                            .build();

                    // Cascade 설정이 ALL 이므로 OrderOption 입장에서 orderItem 을 개별적으로 set 해주지 않아도 됨
                    orderItem.setOrderOptions(orderOptions);
                    return orderItem;
                })
                .toList();

        // Cascade 설정이 ALL 이므로 OrderItem 입장에서 order를 개별적으로 set 해주지 않아도 됨
        // 주문 생성
        orderRepository.save(Order.builder()
                .studio(studio)
                .orderItems(orderItems)
                .user(user)
                .orderDateTime(dto.getOrderDateTime())
                .build());

        return true;
    }
}
