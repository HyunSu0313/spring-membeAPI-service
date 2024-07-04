package cj.cloudwave.member.repository;

import cj.cloudwave.member.domain.Member;
import cj.cloudwave.member.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {
    List<Order> findByMember(Member member);
}
