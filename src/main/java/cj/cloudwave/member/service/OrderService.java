package cj.cloudwave.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

//    private final OrderRepository orderRepository;
//    private final MemberRepository memberRepository;
//    private final ItemRepository itemRepository;
//
//    public Order createOrder(Long memberId, List<OrderItemDto> orderItemDtos) {
//        // 회원 조회
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
//
//        // OrderItemDto 리스트를 OrderItem 리스트로 변환
//        List<OrderItem> orderItems = orderItemDtos.stream()
//                .map(itemDto -> {
//                    // 상품 조회
//                    Item item = itemRepository.findById(itemDto.getItemId())
//                            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
//
//                    // OrderItem 생성
//                    return OrderItem.createOrderItem(item, itemDto.getOrderPrice(), itemDto.getCount());
//                })
//                .collect(Collectors.toList());
//
//        // 주문 생성
//        Order order = Order.createOrder(member, orderItems);
//
//        // 주문 저장
//        orderRepository.save(order);
//
//        return order;
//    }
//
//
//    public void cancelOrder(Long orderId) {
//        // 주문 조회
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
//
//        // 주문 취소
//        order.cancelOrder();
//    }
//
//    @Transactional(readOnly = true)
//    public Order findOrder(Long orderId) {
//        return orderRepository.findById(orderId)
//                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
//    }
//
//    public List<OrderDto> getOrders(Long memberId, OrderStatus status) {
//        Predicate predicate = QOrder.order.member.id.eq(memberId)
//                .and(QOrder.order.status.eq(status));
//
//        List<Order> orders = (List<Order>) orderRepository.findAll(predicate);
//
//        return orders.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    private OrderDto convertToDto(Order order) {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setOrderId(order.getId());
//        orderDto.setStatus(order.getStatus().name());
//        orderDto.setOrderDate(order.getOrderDate());
//
//        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
//                .map(orderItem -> {
//                    OrderItemDto orderItemDto = new OrderItemDto();
//                    orderItemDto.setItemName(orderItem.getItem().getName());
//                    orderItemDto.setOrderPrice(orderItem.getOrderPrice());
//                    orderItemDto.setCount(orderItem.getCount());
//                    return orderItemDto;
//                })
//                .collect(Collectors.toList());
//        orderDto.setOrderItems(orderItemDtos);
//
//        return orderDto;
//    }
}
