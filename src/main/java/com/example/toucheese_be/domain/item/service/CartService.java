package com.example.toucheese_be.domain.item.service;

import com.example.toucheese_be.domain.item.repository.CartItemOptionRepository;
import com.example.toucheese_be.domain.item.repository.CartItemRepository;
import com.example.toucheese_be.domain.item.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemOptionRepository cartItemOptionRepository;

    // 장바구니에 상품 추가 로직
//    @Transactional
//    public ResponseEntity<Boolean> addCartItem(OrderRequestDto dto) {
//        // 사용자 정보 생성 및 저장
//        User user = new User();
//        user.setEmail(orderRequest.getEmail());
//        user.setName(orderRequest.getName());
//        user.setPhoneNumber(orderRequest.getPhone());
//        userRepository.save(user);
//
//        // 스튜디오 조회
//        Studio studio = studioRepository.findById(orderRequest.getStudioId())
//                .orElseThrow(() -> new GlobalCustomException(ErrorCode.STUDIO_NOT_FOUND));
//
//        // 주문 생성
//        Order order = new Order();
//        order.setStudio(studio);
//        order.setUser(user);
//        order.setOrderDateTime(orderRequest.getOrderDateTime());
//
//        // 주문 항목 생성
//        List<OrderItem> orderItems = new ArrayList<>();
//        for (OrderRequestItemDto itemDto : orderRequest.getItemDtos()) {
//            // Item 조회
//            Item item = itemRepository.findById(itemDto.getItemId())
//                    .orElseThrow(() -> new GlobalCustomException(ErrorCode.ITEM_NOT_FOUND));
//
//            // OrderItem 생성
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setItem(item);
//            orderItem.setName(item.getName());
//            orderItem.setQuantity(itemDto.getItemQuantity());
//
//            // 옵션 총 가격 계산
//            int optionsTotalPrice = 0;
//            List<OrderOption> orderOptions = new ArrayList<>();
//            for (OrderRequestOptionDto optionDto : itemDto.getOptionDtoList()) {
//                // ItemOption 조회
//                ItemOption itemOption = itemOptionRepository.findById(optionDto.getOptionId())
//                        .orElseThrow(() -> new GlobalCustomException(ErrorCode.ITEM_OPTION_NOT_FOUND));
//
//                // OrderOption 생성
//                OrderOption orderOption = new OrderOption();
//                orderOption.setOrderItem(orderItem);
//                orderOption.setItemOptionId(itemOption);
//                orderOption.setName(itemOption.getOption().getName());
//                orderOption.setPrice(itemOption.getOption().getPrice());
//                orderOption.setQuantity(optionDto.getOptionQuantity());
//
//                // 옵션 총 가격 계산
//                optionsTotalPrice += itemOption.getOption().getPrice() * optionDto.getOptionQuantity();
//                orderOptions.add(orderOption);
//            }
//
//            // OrderItem에 옵션 설정
//            orderItem.setOrderOptions(orderOptions);
//
//            // 상품 총 가격 (상품 + 옵션)
//            int itemTotalPrice = item.getPrice() * itemDto.getItemQuantity();
//            orderItem.setTotalPrice(itemTotalPrice + optionsTotalPrice);
//
//            orderItems.add(orderItem);
//        }
//        // Order에 OrderItem 설정
//        order.setOrderItems(orderItems);
//
//        // Order 저장
//        orderRepository.save(order);
//        return true;
//
//    }


    // 장바구니 조회 로직

    // 장바구니에 상품 삭제 로직

    // 장바구니 옵션 변경 로직
}
