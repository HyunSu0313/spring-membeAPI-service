package cj.cloudwave.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    private Member(String email, String password, String name, MemberGrade grade) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.grade = grade;
    }

    public static Member createMember(String email, String password, String name) {
        MemberGrade grade = email.contains("@company.com") ? MemberGrade.ADMIN : MemberGrade.CUSTOMER;
        return new Member(email, password, name, grade);
    }
}
