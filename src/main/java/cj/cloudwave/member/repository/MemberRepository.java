package cj.cloudwave.member.repository;

import cj.cloudwave.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
}
