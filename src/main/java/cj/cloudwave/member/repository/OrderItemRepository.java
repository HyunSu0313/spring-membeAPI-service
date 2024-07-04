package cj.cloudwave.member.repository;

import cj.cloudwave.member.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
