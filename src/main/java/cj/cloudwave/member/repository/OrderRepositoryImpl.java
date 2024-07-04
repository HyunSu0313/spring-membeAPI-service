package cj.cloudwave.member.repository;

import cj.cloudwave.member.domain.Order;
import cj.cloudwave.member.domain.OrderStatus;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cj.cloudwave.member.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public List<Order> findAllOrders(Long memberId, OrderStatus status) {
        return queryFactory
                .selectFrom(order)
                .where(
                        memberIdEq(memberId),
                        statusEq(status)
                )
                .fetch();
    }

    private Predicate memberIdEq(Long memberId) {
        return memberId != null ? order.member.id.eq(memberId) : null;
    }

    private Predicate statusEq(OrderStatus status) {
        return status != null ? order.status.eq(status) : null;
    }
}
