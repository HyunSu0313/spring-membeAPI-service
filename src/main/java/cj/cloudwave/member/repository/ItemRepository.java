package cj.cloudwave.member.repository;

import cj.cloudwave.member.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContainingIgnoreCase(String keyword);
    Optional<Item> findById(Long id);
}
