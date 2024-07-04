package cj.cloudwave.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private Order(Member member, List<OrderItem> orderItems) {
        this.member = member;
        this.status = OrderStatus.ORDER;
        this.orderDate = LocalDateTime.now();
        this.orderItems = orderItems;
        this.orderItems.forEach(orderItem -> orderItem.setOrder(this));
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    private void setStatus() {
        this.status = OrderStatus.ORDER;
    }

    private void setOrderDate() {
        this.orderDate = LocalDateTime.now();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member,  OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus();
        order.setOrderDate();
        return order;
    }

    public void cancelOrder() {
        if (this.status == OrderStatus.CANCEL) {
            throw new IllegalStateException("이미 취소된 주문입니다.");
        }
        this.status = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
