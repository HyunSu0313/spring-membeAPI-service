package cj.cloudwave.member.service;

import cj.cloudwave.member.domain.Member;
import cj.cloudwave.member.domain.MemberGrade;
import cj.cloudwave.member.dto.LoginDto;
import cj.cloudwave.member.dto.MemberDto;
import cj.cloudwave.member.exception.IdDuplicateException;
import cj.cloudwave.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void registerMember(MemberDto memberDto) {
        // 중복된 이메일로 회원 가입 불가
        if (memberRepository.existsByEmail(memberDto.getEmail())) {
            throw new IllegalArgumentException("Already exists member with email: " + memberDto.getEmail());
        }

        // 비밀번호 암호화
        String encryptedPassword = PasswordUtil.encodePassword(memberDto.getPassword());

        // 회원 데이터 생성
        Member member = Member.createMember(memberDto.getEmail(), encryptedPassword, memberDto.getName());

        // 회원 저장
        memberRepository.save(member);
    }

    public Member authenticate(LoginDto loginDto) {
        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElse(null);

        if (member != null && PasswordUtil.matches(loginDto.getPassword(), member.getPassword())) {
            return member;
        }
        return null;
    }
}
