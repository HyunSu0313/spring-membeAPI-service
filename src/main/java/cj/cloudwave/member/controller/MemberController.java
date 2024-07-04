package cj.cloudwave.member.controller;

import cj.cloudwave.member.domain.Member;
import cj.cloudwave.member.dto.LoginDto;
import cj.cloudwave.member.dto.MemberCheckDto;
import cj.cloudwave.member.dto.MemberDto;
import cj.cloudwave.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있을 경우
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.append(error.getDefaultMessage()).append(". ");
            });
            return ResponseEntity.badRequest().body(errors.toString());
        }

        try {
            memberService.registerMember(memberDto);
            return ResponseEntity.ok("Member registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Spring Security 없이 간단한 로그인, 로그아웃 구현
     * Spring Boot 기본 세션 타임아웃 30분
     */
    // login 구현
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginRequest, HttpSession session) {
        Member member = memberService.authenticate(loginRequest);
        if (member != null) {
            session.setAttribute("member", member);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // logout 구현
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/member")
    public ResponseEntity<MemberCheckDto> getMember(HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            MemberCheckDto memberCheckDto = new MemberCheckDto();
            memberCheckDto.setId(member.getId());
            memberCheckDto.setEmail(member.getEmail());
            memberCheckDto.setName(member.getName());
            memberCheckDto.setGrade(member.getGrade());
            return ResponseEntity.ok(memberCheckDto);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
