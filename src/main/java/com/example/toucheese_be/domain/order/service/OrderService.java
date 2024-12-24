package com.example.toucheese_be.domain.order.service;


import com.example.toucheese_be.domain.item.dto.ItemDto;
import com.example.toucheese_be.domain.order.dto.*;
import com.example.toucheese_be.domain.order.entity.constant.OrderStatus;
import com.example.toucheese_be.domain.user.entity.PrincipalDetails;
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
import com.example.toucheese_be.global.common.constant.ErrorCode;
import com.example.toucheese_be.global.common.AuthenticationFacade;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final StudioRepository studioRepository;
    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;
    private final OrderRepository orderRepository;
    private final AuthenticationFacade authFacade;

    // 추후 querydsl 로 쿼리 성능 최적화할 예정
    @Transactional
    public boolean createOrder(OrderRequestDto dto) {
        PrincipalDetails principalDetails = authFacade.getAuth();

        User user = userRepository.findById(principalDetails.getUserId())
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.USER_NOT_FOUND));

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

    // 예약 취소하기 메서드
    public Boolean getCancelTheSchedule(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.ORDER_NOT_FOUND));
//                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.setStatus(OrderStatus.CANCEL_RESERVATION);
        orderRepository.save(order);

        return true;
    }

    // 예약 일정 탭 메서드
    public List<OrderDetailDto> checkedSchedule() {
        // 사용자 예약 주문을 사용자 ID로 조회 (최적화된 쿼리 사용)
        PrincipalDetails principalDetails = authFacade.getAuth();
        List<Order> orders = orderRepository.findByUserIdWithDetails(principalDetails.getUserId());

        List<OrderDetailDto> schedule = new ArrayList<>(); // 단일 리스트로 변경

        // 주문이 없는 경우 빈 리스트를 반환
        if (orders.isEmpty()) {
            return schedule;
        }

        // 주문 상태에 따라 리스트에 추가
        for (Order order : orders) {
            // 주문 상품 정보 DTO 생성
            List<OrderItemDto> orderItemDtos = new ArrayList<>();

            // 스튜디오 이름 초기화
            String studioName = "정보 없음"; // 기본값 설정
            if (order.getStudio() != null) {
                studioName = order.getStudio().getName(); // 스튜디오 정보 가져오기
            }

            for (OrderItem orderItem : order.getOrderItems()) {
                Item item = orderItem.getItem();

                OrderItemDto orderItemDto = OrderItemDto.builder()
                        .itemId(item != null ? item.getId() : null)
                        .itemName(item != null ? item.getName() : "정보 없음")
                        .build();

                orderItemDtos.add(orderItemDto);
            }

            // 사용자 정보 DTO 생성
            OrderUserDto orderUserDto = OrderUserDto.builder()
                    //.userId(order.getId() != null ? order.getUser().getId() : null)
                    .userName(order.getUser() != null ? order.getUser().getUsername() : null)
                    //.userEmail(order.getUser() != null ? order.getUser().getEmail() : null)
                    //.userPhone(order.getUser() != null ? order.getUser().getPhone() : null)
                    .build();

            // 최종 DTO 생성
            OrderDetailDto detailDto = OrderDetailDto.builder()
                    .orderId(order.getId())
                    .orderUserDto(orderUserDto)
                    .reservedDateTime(order.getOrderDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) // 예약 시간
                    .studioName(studioName) // 스튜디오 이름
                    .orderItemDto(orderItemDtos) // 주문 상품 DTO 리스트
                    .build();

            schedule.add(detailDto); // 리스트에 추가
        }
        return schedule; // 단일 리스트 반환
    }


    // 예약 일정 상세보기
    public List<OrderDetailDto> viewDetailedSchedule() {
        // 사용자 예약 주문을 사용자 ID로 조회 (최적화된 쿼리 사용)
        PrincipalDetails principalDetails = authFacade.getAuth();
        List<Order> orders = orderRepository.findByUserIdWithDetails(principalDetails.getUserId());

        // 단일 리스트로 변경
        List<OrderDetailDto> detailedSchedule = new ArrayList<>();

        // 주문이 없는 경우 빈 리스트를 반환
        if (orders.isEmpty()) {
            return detailedSchedule;
        }

        // 주문 상태에 따라 리스트에 추가
        for (Order order : orders) {
            // 주문 상품 정보 DTO 생성
            List<OrderItemDto> orderItemDtos = new ArrayList<>();

            // 스튜디오 이름 초기화
            String studioName = "정보 없음"; // 기본값 설정
            if (order.getStudio() != null) {
                studioName = order.getStudio().getName(); // 스튜디오 정보 가져오기
            }

            for (OrderItem orderItem : order.getOrderItems()) {
                Item item = orderItem.getItem();

                // 주문 옵션 DTO 리스트 초기화
                List<OrderOptionDto> orderOptionDtos = new ArrayList<>();
                if (orderItem != null // && orderItem.getOrderOptions() != null
                 ) {
                    for (OrderOption option : orderItem.getOrderOptions()) {
                        // 주문 옵션 DTO 생성
                        OrderOptionDto optionDto = new OrderOptionDto(
                                option.getId(),
                                option.getName(),
                                option.getPrice(),
                                option.getQuantity());
                        orderOptionDtos.add(optionDto); // 리스트에 추가
                    }

                    OrderItemDto orderItemDto = OrderItemDto.builder()
                            .itemName(item != null ? item.getName() : "정보 없음")
                            .itemImage(item != null ? item.getImage() : "정보 없음")
                            .quantity(item != null ? orderItem.getQuantity() : null)
                            .totalPrice(item != null ? orderItem.getTotalPrice() : null)
                            .orderOptionDtos(orderOptionDtos)
                            .build();

                    orderItemDtos.add(orderItemDto);
                }

                // 사용자 정보 DTO 생성
                OrderUserDto orderUserDto = OrderUserDto.builder()
                        .userName(order.getUser() != null ? order.getUser().getUsername() : "정보 없음")
                        .userEmail(order.getUser().getEmail() != null ? order.getUser().getEmail() : "정보 없음")
                        .userPhone(order.getUser().getPhone() != null ? order.getUser().getPhone() : "정보 없음")
                        .build();

                // 최종 DTO 생성
                OrderDetailDto detailDto = OrderDetailDto.builder()
                        .orderId(order.getId())
                        .orderUserDto(orderUserDto)
                        .reservedDateTime(order.getOrderDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .studioName(studioName)
                        .orderItemDto(orderItemDtos)
                        .build();

                detailedSchedule.add(detailDto);
            }
        }
        return detailedSchedule;
    }
}
    /*

    // 예약 일정 탭 구현


//
////
////            if (orderStatus.equals(OrderStatus.KEEP_RESERVATION)) {
////                // "다가오는 예약 일정"에 추가
////                scheduleMap.get("다가오는 예약 일정").addAll(orderDetailDtos);
////            } else if (orderStatus.equals(OrderStatus.CONFIRM_RESERVATION)) {
////                // "이전 예약 일정"에 추가
////                scheduleMap.get("이전 예약 일정").addAll(orderDetailDtos);
////            } else if (orderStatus.equals(OrderStatus.CANCEL_RESERVATION)) {
////                // "이전 예약 일정"에 추가
////                scheduleMap.get("이전 예약 일정").addAll(orderDetailDtos);
////            }else {
////
////            }
//
//        }
//
//        return scheduleMap;
//    }

    public List<OrderDetailDto> checkedSchedule() {
        // 사용자 예약 주문을 사용자 ID로 조회 (최적화된 쿼리 사용)
        PrincipalDetails principalDetails = authFacade.getAuth();
        List<Order> orders = orderRepository.findByUserIdWithDetails(principalDetails.getUserId());

        List<List<OrderDetailDto>> schedule = new ArrayList<>();
//        scheduleMap.put("다가오는 예약 일정", new ArrayList<>());
//        scheduleMap.put("이전 예약 일정", new ArrayList<>());

        // 주문이 없는 경우 빈 리스트를 반환
        if (orders.isEmpty()) {
            return schedule;
        }

        // 주문 상태에 따라 리스트에 추가
        for (Order order : orders) {

            // 주문 상품 정보 DTO 생성
            List<OrderItemDto> orderItemDtos = new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItems()) {
                Item item = orderItem.getItem();
                Studio studio = item.getStudio(); // 스튜디오 정보

                OrderItemDto orderItemDto = OrderItemDto.builder()
                        .itemId(item.getId())
                        .itemName(item.getName())
                        //.totalPrice(orderItem.getTotalPrice())
                        .build();

                 orderItemDtos.add(orderItemDto);


            }
            // 사용자 정보 DTO 생성
            OrderUserDto orderUserDto = OrderUserDto.builder()
                    //.userId(order.getUser() != null ? order.getUser().getId() : null)
                    .userName(order.getUser() != null ? order.getUser().getUsername() : null)
                    //.userEmail(order.getUser() != null ? order.getUser().getEmail() : null)
                    //.userPhone(order.getUser() != null ? order.getUser().getPhone() : null)
                    .build();

            // 최종 DTO 생성
            OrderDetailDto detailDto = OrderDetailDto.builder()
                    .orderId(order.getId())
                    .orderUserDto(orderUserDto)
                    .reservedDateTime(order.getOrderDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) // 예약 시간
                    .studioName(studio.getName()) // 스튜디오 이름
                    .orderItemDto(orderItemDtos) // 주문 상품 DTO 리스트
                    .build();

            schedule.add(detailDto);
        }
        return schedule;
    }

        // 사용자 예약 주문을 사용자 ID로 조회
        List<Order> orders = orderRepository.findByUserId(userId);

        Map<String, List<OrderDetailDto>> scheduleMap = new HashMap<>();
        scheduleMap.put("다가오는 예약 일정", new ArrayList<>());
        scheduleMap.put("이전 예약 일정", new ArrayList<>());

        // 주문이 없는 경우 빈 리스트를 반환

        if (orders.isEmpty()) {
            return scheduleMap;
        }
        // 주문 상태에 따라 리스트에 추가
        for (Order order : orders) {
            OrderStatus orderStatus = order.getStatus();

            // 주문 세부 정보를 DTO로 변환
            List<OrderDetailDto> orderDetails = order.getOrderDetails();

            // 주문 상태에 따라 리스트에 추가
            switch (orderStatus) {
                case KEEP_RESERVATION:
                    scheduleMap.get("다가오는 예약 일정").addAll(orderDetails);
                    break;

                case CANCEL_RESERVATION:
                case FINISHED_FILM:
                case CONFIRM_RESERVATION:
                    scheduleMap.get("이전 예약 일정").addAll(orderDetails);
                    break;


            }*/

    /*

    schedule.add(0, new ArrayList<>());
        schedule.add(1, new ArrayList<>());
        schedule.add(2, new ArrayList<>());
        schedule.add(3, new ArrayList<>());

                // 주문 상태에 따라 리스트에 추가
                switch (orderStatus) {
                    case KEEP_RESERVATION:
                        schedule.get(0).add(detailDto);
                        break;

                    case CANCEL_RESERVATION:
                        schedule.get(1).add(detailDto);
                        break;
                    case CONFIRM_RESERVATION:
                        schedule.get(2).add(detailDto);
                        break;
                    case FINISHED_FILM:
                        schedule.get(3).add(detailDto);
                        break;

                }*/


