package cj.cloudwave.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;

    private OrderItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        item.removeStock(count);
        return new OrderItem(item, orderPrice, count);
    }

    void setOrder(Order order) {
        this.order = order;
    }

    // 주문 취소하면 item의 stock 증가
    public void cancel() {
        getItem().addStock(count);
    }

    // 주문 전체 금액 결과
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
