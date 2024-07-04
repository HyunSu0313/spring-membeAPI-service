package cj.cloudwave.member.dto;

import cj.cloudwave.member.domain.MemberGrade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCheckDto {
    private Long id;
    private String email;
    private String name;
    private MemberGrade grade;
}
